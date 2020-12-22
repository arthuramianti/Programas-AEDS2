import jdk.nashorn.api.tree.ForInLoopTree;

class TP03Q10{
    public static class Celula {
        public int elemento;
        public Celula inf, sup, esq, dir;
     
        public Celula(){
           this(0, null, null, null, null);
        }
     
        public Celula(int elemento){
           this(elemento, null, null, null, null);
        }
     
        public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
           this.elemento = elemento;
           this.inf = inf;
           this.sup = sup;
           this.esq = esq;
           this.dir = dir;
        }
    }

    public static class Matriz{
        private Celula inicio;
        private int linha, coluna;

        public Matriz (){
            new Matriz(2, 2);
        }

        public Matriz (int linha, int coluna){
            this.linha = linha;
            this.coluna = coluna;
            this.inicio = new Celula();
            Celula tmp1 = this.inicio;
            Celula tmp2;
            Celula tmp3;
            for(int l = 0; l < linha ; l++){
              tmp2 = tmp1;
              tmp3 = tmp1.sup;
              for(int c = 0; c < coluna -1 ; c++){
                    tmp2.dir = new Celula();
                    tmp2.dir.esq = tmp2;
                    tmp2 = tmp2.dir;
                    if(l > 0){
                        tmp3 = tmp3.dir;
                        tmp2.sup = tmp3;
                        tmp3.inf = tmp2;
                    }
              }
              tmp1.inf = new Celula();
              tmp1.inf.sup = tmp1;
              tmp1 = tmp1.inf;
            }
      
        }

        public void ler(){
            Celula tmp = inicio;
            tmp.elemento = 3;
            tmp.dir.elemento = 4;
            tmp.inf.elemento = 5;
            tmp.inf.dir.elemento = 6;
        }

        public void imprimir(){
            int lin = this.linha;
            int col = this.coluna;
            Celula i = inicio;
            Celula aux;
            for(int l = 0; l < lin; l++){
                aux = i;
                if(l > 0 && l < this.linha){
                    i = i.inf;
                }
                    for(int m = 0; m < col; m++){
                        System.out.print(i.elemento + " ");
                        i = i.dir;
                    }
                    System.out.println();
                i = aux;
            }
        
        }

        public Matriz soma(Matriz m){
            Matriz resultado = new Matriz(2, 2);
            Celula fodas = resultado.inicio;
            Celula i = this.inicio;
            Celula aux1 = m.inicio;
            Celula aux;
            Celula aux2;
            Celula aux3;
            if(m.isQuadrada() && this.isQuadrada()){
                for(int l = 0; l < this.linha; l++){
                    aux = i;
                    aux2 = aux1;
                    aux3 = fodas;
                    if(l > 0 && l < this.linha){
                        i = i.inf;
                        aux1 = aux1.inf;
                        fodas = fodas.inf;
                    }
                        for(int j = 0; j < this.coluna; j++){
                            fodas.elemento = i.elemento + aux1.elemento;
                            i = i.dir;
                            aux1 = aux1.dir;
                            fodas = fodas.dir;
                        }
                        i = aux;
                        aux1 = aux2;
                        fodas = aux3;
                }
            }

            return resultado;

        }

        public boolean isQuadrada(){
            return (this.linha == this.coluna);
        }

        public void diagonalPrincipal(){
            Celula j = inicio;
            for(int i = 0; i < this.linha; i++){
                if(i > 0 && i < this.linha){
                j = j.dir.inf;
                System.out.print(j.elemento);
                }else
                System.out.print(j.elemento);
            }
        }


    }
    


    public static void main(String[] args){
        Matriz matriz = new Matriz(2, 2);
        Matriz m = new Matriz(2, 2);
        matriz.ler();
        m.ler();
        Matriz soma = matriz.soma(m);
        soma.diagonalPrincipal();
    }
}
