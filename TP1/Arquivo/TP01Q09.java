import java.io.File;
import java.io.RandomAccessFile;

class TP01Q09{
    
    public static void leArquivo(int numOperacoes, double real, Double[] entrada){
        double numero = 0;

        try{
            RandomAccessFile file = new RandomAccessFile("teste.txt", "rw");
            
            for(int i = 0; i < numOperacoes; i++){
                real = entrada[i];
                file.writeDouble(real);
            }
                     
        for(long y = (file.getFilePointer() - 8); y >= 0; y-=8){
            file.seek(y);
            numero = file.readDouble();
            if( (numero - (int)numero) == 0)
                MyIO.println((int)numero);
            else
                MyIO.println(numero);  
            }

            file.close();

        } catch (Exception e) {}
            
    }
    
    public static void main(String args[])throws Exception{
        Double[] entrada = new Double[1000]; 
        int numOperacoes, aux = 0;
        double real = 0;

        numOperacoes = MyIO.readInt();

        do{
            entrada[aux++] = MyIO.readDouble();
        }while(aux < numOperacoes);
           
        leArquivo(numOperacoes, real, entrada);    
        
    }
}
