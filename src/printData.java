
public class printData {
    public static ReadData readdat = new ReadData("192.168.2.1:5433/test_dl","postgres","root","power_4yr");
    public static void main( String args[] ) {
        readdat.read();

        System.out.println(readdat.rowNum);

        for(int i = 0; i < 20; i = i+1){
            for(int j = 0; j < 96; j = j+1){
                System.out.print(readdat.data[i][j]+" ");
            }
            System.out.println();
        }
    }
}
