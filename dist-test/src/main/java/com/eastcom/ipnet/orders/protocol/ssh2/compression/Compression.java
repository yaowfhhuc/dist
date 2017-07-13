package com.eastcom.ipnet.orders.protocol.ssh2.compression;

import com.eastcom.ipnet.orders.protocol.jzlib.JZlib;
import com.eastcom.ipnet.orders.protocol.jzlib.ZStream;

public class Compression implements com.eastcom.ipnet.orders.protocol.ssh2.Compression {
	static private final int BUF_SIZE = 4096;

	@SuppressWarnings("unused")
	private int type;
	private ZStream stream;
	private byte[] tmpbuf = new byte[BUF_SIZE];

	public Compression() {
		stream = new ZStream();
	}

	public void init(int type, int level) {
		if (type == DEFLATER) {
			stream.deflateInit(level);
			this.type = DEFLATER;
		} else if (type == INFLATER) {
			stream.inflateInit();
			inflated_buf = new byte[BUF_SIZE];
			this.type = INFLATER;
		}
	}

	/*
	 * static Compression getDeflater(int level){ Compression foo=new
	 * Compression(); foo.stream.deflateInit(level); foo.type=DEFLATER; return
	 * foo; }
	 */
	private byte[] inflated_buf;

	/*
	 * static Compression getInflater(){ Compression foo=new Compression();
	 * foo.stream.inflateInit(); foo.inflated_buf=new byte[BUF_SIZE];
	 * foo.type=INFLATER; return foo; }
	 */

	public int compress(byte[] buf, int start, int len) {
		stream.next_in = buf;
		stream.next_in_index = start;
		stream.avail_in = len - start;
		int status;
		int outputlen = start;

		do {
			stream.next_out = tmpbuf;
			stream.next_out_index = 0;
			stream.avail_out = BUF_SIZE;
			status = stream.deflate(JZlib.Z_PARTIAL_FLUSH);
			switch (status) {
			case JZlib.Z_OK:
				System.arraycopy(tmpbuf, 0, buf, outputlen, BUF_SIZE
						- stream.avail_out);
				outputlen += (BUF_SIZE - stream.avail_out);
				break;
			default:
				System.err.println("compress: deflate returnd " + status);
			}
		} while (stream.avail_out == 0);
		return outputlen;
	}

	public byte[] uncompress(byte[] buffer, int start, int[] length) {
		int inflated_end = 0;

		stream.next_in = buffer;
		stream.next_in_index = start;
		stream.avail_in = length[0];

		while (true) {
			stream.next_out = tmpbuf;
			stream.next_out_index = 0;
			stream.avail_out = BUF_SIZE;
			int status = stream.inflate(JZlib.Z_PARTIAL_FLUSH);
			switch (status) {
			case JZlib.Z_OK:
				if (inflated_buf.length < inflated_end + BUF_SIZE
						- stream.avail_out) {
					byte[] foo = new byte[inflated_end + BUF_SIZE
							- stream.avail_out];
					System.arraycopy(inflated_buf, 0, foo, 0, inflated_end);
					inflated_buf = foo;
				}
				System.arraycopy(tmpbuf, 0, inflated_buf, inflated_end,
						BUF_SIZE - stream.avail_out);
				inflated_end += (BUF_SIZE - stream.avail_out);
				length[0] = inflated_end;
				break;
			case JZlib.Z_BUF_ERROR:
				if (inflated_end > buffer.length - start) {
					byte[] foo = new byte[inflated_end + 5];
					System.arraycopy(buffer, 0, foo, 0, start);
					System.arraycopy(inflated_buf, 0, foo, 5, inflated_end);
					buffer = foo;
				} else {
					System.arraycopy(inflated_buf, 0, buffer, 5, inflated_end);
				}
				length[0] = inflated_end;
				return buffer;
			default:
				System.err.println("uncompress: inflate returnd " + status);
				return null;
			}
		}
	}
}
