package orders.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class GetFileFromLocalAnalysis
{
    public static final byte LF = 10;
    public static final byte CR = 13;
    private int position = 0;
    protected byte[] buffer;

    public GetFileFromLocalAnalysis(String path)
    {
    }

    protected void getFile(String path, String date)
      throws Exception
    {
      System.out.println("Path :" + path);
      File file = new File(path);
      if (file.getName().indexOf(date) >= 0) {
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while (in.available() != 0) {
          out.write(in.read());
        }
        out.flush();
        in.close();
        out.close();
        this.buffer = out.toByteArray();
      }
    }

    protected String getNextLine() {
        String line =null;
      if (this.position >= this.buffer.length) {
        return null;
      }
      int end = this.position;
      int cur = this.position;
      boolean done = false;
      do {
        end++;
        if ((this.buffer[cur] == 10) && (cur + 1 < this.buffer.length) && (this.buffer[(cur + 1)] == 13))
        {
          end--;
          cur += 2;
          done = true;
        } else if ((this.buffer[cur] == 13) && (cur + 1 < this.buffer.length) && (this.buffer[(cur + 1)] == 10))
        {
          end--;
          cur += 2;
          done = true;
        } else if ((this.buffer[cur] == 10) || (this.buffer[cur] == 13)) {
          end--;
          cur++;
          done = true;
        } else {
          cur++;
        }
      }
      while (!done);
      line = new String(this.buffer, this.position, end - this.position);
      this.position = cur;
      return line;
    }
}
