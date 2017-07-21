public class printData {
    public ReadData dat = new ReadData();
    dat.read();
    public static void main( String args[] ) {
        for(int i = 0; i < 20; i = i+1){
            for(int j = 0; j < 96; j = j+1){
                System.out.print(data[i][j]);
            }
        }
    }
}
