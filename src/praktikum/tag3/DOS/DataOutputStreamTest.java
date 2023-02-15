package praktikum.tag3.DOS;

import java.io.*;

public class DataOutputStreamTest {
    public static void main(String[] args) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("./test.txt"))) {
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeShort((short) 12);
            dataOutputStream.writeInt(13);
            dataOutputStream.writeLong(14L);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("./test.txt"))) {
            System.out.println(dataInputStream.readBoolean());
            System.out.println(dataInputStream.readShort());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (PrintStream printStream = new PrintStream(".testStream.txt")) {
            printStream.println(false);
            printStream.println(11);
            printStream.print(Integer.MAX_VALUE);
            //printStream.write(13);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
