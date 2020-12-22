import java.util.ArrayList;

class Questao2{
    public static class Lista {
        private String[] array;
        private int n;
     
     
        public Lista () {
           this(1000);
        }
     
     
        
        public Lista (int tamanho){
           array = new String[tamanho];
           n = 0;
        }
     
     
        
        public void inserirInicio(String elem) throws Exception {
     
           //validar insercao
           if(n >= array.length){
              throw new Exception("Erro ao inserir!");
           } 
     
           //levar elementos para o fim do array
           for(int i = n; i > 0; i--){
              array[i] = array[i-1];
           }
     
           array[0] = elem;
           n++;
        }
     
     
        
        public void inserirFim(String elem) throws Exception {
     
           //validar insercao
           if(n >= array.length){
              throw new Exception("Erro ao inserir!");
           }
     
           array[n] = elem;
           n++;
        }
     
     
        
        public void inserir(String elem, int pos) throws Exception {
     
           //validar insercao
           if(n >= array.length || pos < 0 || pos > n){
              throw new Exception("Erro ao inserir!");
           }
     
           //levar elementos para o fim do array
           for(int i = n; i > pos; i--){
              array[i] = array[i-1];
           }
     
           array[pos] = elem;
           n++;
        }
     
     
       
        public String removerInicio() throws Exception {
     
           //validar remocao
           if (n == 0) {
              throw new Exception("Erro ao remover!");
           }
     
           String resp = array[0];
           n--;
     
           for(int i = 0; i < n; i++){
              array[i] = array[i+1];
           }
     
           return resp;
        }
     
     
        
        public String removerFim() throws Exception {
     
           //validar remocao
           if (n == 0) {
              throw new Exception("Erro ao remover!");
           }
     
           return array[--n];
        }
     
     
        
        public String remover(int pos) throws Exception {
     
           //validar remocao
           if (n == 0 || pos < 0 || pos >= n) {
              throw new Exception("Erro ao remover!");
           }
     
           String resp = array[pos];
           n--;
     
           for(int i = pos; i < n; i++){
              array[i] = array[i+1];
           }
     
           return resp;
        }
     
     
        public void mostrar (){
           
           for(int i = 0; i < n; i++){
              System.out.println(array[i] + " ");
           }
           
        }
     
     
        
        public boolean pesquisar(String elem) {
           boolean retorno = false;
           for (int i = 0; i < n && retorno == false; i++) {
              retorno = (array[i] == elem);
           }
           return retorno;
        }

        
     }

     public static void trataLista(String elemento){
        String[] resp = elemento.split(" ");
        int tamanho = resp.length;
        
        for(int i = 0; i < tamanho - 1; i++){
            String elem = resp[i];
            for(int k = i + 1; k < tamanho; k++){
                if(resp[k].equals(elem)){
                    resp[k] = "";
                }
            }
        }
        
       /* for(int m = 0; m < resp.length; m++){                                //NAO CONSEGUI APLICAR O ALGORITMO DE ORDENAÇAO PARA ORDENAR O ARRAY.
            int menor = m;
            for(int l = m + 1; l < resp.length; m++){
                 if(resp[menor].compareTo(resp[l]) > 0){
                    menor = l;
                }
            }
            String aux = resp[menor];
            resp[menor] = resp[m];
            resp[m] = aux;
        }*/                                                                 //NAO CONSEGUI APLICAR O ALGORITMO DE ORDENAÇAO PARA ORDENAR O ARRAY
        
        
        String resposta = "";
        for(int l = 0; l < tamanho; l++){
            resposta += resp[l] + " "; 
        }
        MyIO.println(resposta);
            
    }



    public static void main(String[] args)throws Exception{
        String[] entrada = new String[1000];
        int numEntrada = 0;
        int numOperacoes = 0;
        int aux = 100;

        Lista lista = new Lista();
        numOperacoes = MyIO.readInt();
        
        do{
            entrada[numEntrada] = MyIO.readLine();
            numEntrada++;
        }while(numEntrada < numOperacoes);

        for(int i = 0; i < numOperacoes; i++){
            lista.inserirFim(entrada[i]);
        }

        for(int i = 0; i < numOperacoes; i++){
            trataLista(entrada[i]);

        }
        
    }
}
