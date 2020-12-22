class Matriz{
   private Celula inicio;
   private int linha, coluna;

   public Matriz (){
      this(4, 4);
   }

   public Matriz (int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;

      inicio = new CelulaMatriz();
      int cont1= 0;
     
      //primeiraLinha(int elemento){
      for(CelulaMatriz i = inicio; cont1 < coluna-1; cont1++; i = i.dir){
          i.dir = new CelulaMatriz(elemento);
          i.dir.esq = i;
      }

      cont1 = 0;
      //faz as outras linhas
      for(CelulaMatriz i = inicio; cont1 < linha-1; cont1++; i = i.inf){
          i.inf = new CelulaMatriz();
          i.inf.sup = i;

          int con2 = 0;
      //conecta todos os ponteiros das celulas
      for(CelulaMatriz k = i.inf, i = i.dir; cont2 < coluna-1; cont2++, k = k.dir, i = i.dir){
          k.dir = new CelulaMatriz();
          k.dir.esq = k;
          k.dir.sup = i;
          k.dir.sup.inf = k.dir;
      }    
      }
   }

  public static class CelulaMatriz {
    public int elemento;
    public Celula inf, sup, esq, dir;
 
    public CelulaMatriz(){
       this(0, null, null, null, null);
    }
 
    public CelulaMatriz(int elemento){
       this(elemento, null, null, null, null);
    }
 
    public CelulaMatriz(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
       this.elemento = elemento;
       this.inf = inf;
       this.sup = sup;
       this.esq = esq;
       this.dir = dir;
    }
  }

  public static void main(String[] args){
      String[] entrada = new entrada[1000];
      int numEntrada = 0;

      do{
          entrada[numEntrada] = MyIO.readLine();
      }while(isFim(entrada[numEntrada++]) == false);

        String[] split = entrada[0].split(" ");
        int linhas = Integer.parseInt(split[0]);
        int colunas = Integer.parseInt(split[1]);
        Matriz matriz = new Matriz(linhas, colunas);

      for(int i = 1; i < numEntrada; i++){
          String[] split1 = entrada[i].split(" ");
          matriz.CelulaMatriz(Integer.parseInt(split[0]));
          matriz.CelulaMatriz(Integer.parseInt(split[1]));
          matriz.CelulaMatriz(Integer.parseInt(split[2]));
          matriz.CelulaMatriz(Integer.parseInt(split[3]));
      }

      
  }

}
