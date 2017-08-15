public class run_single {
    public static double[][] data;
    public static ReadData readdat = new ReadData("192.168.2.1:5433/test_dl","postgres","root","power_4yr");
    private static int nrow, ncol;

    public run_single(double[][] dat){
        data = dat;
        nrow = data.length;
        ncol = data[0].length;
    }

    private static Boolean checkzero(double[] vec){
        int cnt = 0;
        for (int i = 0; i <vec.length; i = i+1){
            if(vec[i]==0){
                cnt = cnt+1;
            }
        }
        return cnt>48;
    }

    private static void fill(int rownum){
        double[] vec = data[rownum];
        int chtag = 0;
        int count = 0;
        int start;
        if(vec[chtag]==0){
            for(chtag = 0; chtag<vec.length;chtag=chtag+1){
                if(vec[chtag]!=0){
                    break;
                }
            }
            for(int i = 0; i<chtag; i=i+1) {
                vec[i] = vec[chtag];
            }
        }
        while(chtag<vec.length){
            for(chtag = chtag; chtag<vec.length;chtag = chtag+1){
                if(vec[chtag]==0 || chtag==vec.length) {
                    break;
                }
            }
            start = chtag-1;
            count = 2;
            for(chtag = chtag; chtag<vec.length;chtag = chtag+1){
                if(vec[chtag]!=0) {
                    break;
                }
                count = count + 1;
            }
            if(chtag==vec.length &&start<vec.length-1){
                for(int i = start+1;i<vec.length;i=i+1){
                    vec[i]=vec[start];
                }
            }else if (start<vec.length-1) {
                for (int i = start+1; i < chtag; i = i + 1) {
                    vec[i] = vec[start] + (vec[chtag] - vec[start]) / count * (i-start);
                }
            }
        }
        data[rownum]=vec;
    }
    public static void main( String args[]){
        readdat.read();
        data=readdat.data;
        for(int i=0;i<400;i=i+1){
            if(!checkzero(data[i])){
                fill(i);
                for(int j=0;j<96;j=j+1){
                    System.out.print(data[i][j]+" ");
                }
                System.out.println();
            }
        }
    }
}
