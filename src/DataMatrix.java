public class DataMatrix implements Runnable{
    public static double[][] data;
    private static int nrow, ncol;
    public int s, e;

    public DataMatrix(double[][] dat,int sp,int ep){
        data = dat;
        nrow = data.length;
        ncol = data[0].length;
        s = sp;
        e = ep;
    }

    private Boolean checkzero(double[] vec){
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
    @Override
    public void run(){
        for(int i=s;i<e;i=i+1){
            if(checkzero(data[i])){
                fill(i);
            }
            for(int j=0;j<96;j=j+1){
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
    }

}
