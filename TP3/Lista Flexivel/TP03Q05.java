class TP03Q05{
    public static class Jogador{
        private  int id;
        private  String nome;
        private  int altura;
        private  int peso;
        private  String universidade;
        private  int anoNascimento;
        private  String cidadeNascimento;
        private  String estadoNascimento;
    
        public int getId(){
            return this.id;
        }
    
        public void setId(int id){
            this.id = id;
        }
    
        public String getNome(){
            return this.nome;
        }
    
        public void setNome(String nome){
            this.nome = nome;
        }
    
        public int getAltura(){
            return this.altura;
        }
    
        public void setAltura(int altura){
            this.altura = altura;
        }
    
        public int getPeso(){
            return this.peso;
        }
    
        public void setPeso(int peso){
            this.peso = peso;
        }
    
        public String getUniversidade(){
            return this.universidade;
        }
    
        public void setUniversidade(String universidade){
            this.universidade = universidade;
        }
    
        public int getAnoNascimento(){
            return this.anoNascimento;
        }
    
        public void setAnoNascimento(int anoNascimento){
            this.anoNascimento = anoNascimento;
        }
    
        public String getCidadeNascimento(){
            return this.cidadeNascimento;
        }
    
        public void setCidadeNascimento(String cidadeNascimento){
            this.cidadeNascimento = cidadeNascimento;
        }
    
        public String getEstadoNascimento(){
            return this.estadoNascimento;
        }
    
        public void setEstadoNascimento(String estadoNascimento){
            this.estadoNascimento = estadoNascimento;
        }
    
        public Jogador(){
            this(0, "", 0, 0, "", 0, "", "");
        }
    
        public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento){
            this.id = id;
            this.nome = nome;
            this.altura = altura;
            this.peso = peso;
            this.universidade = universidade;
            this.anoNascimento = anoNascimento;
            this.cidadeNascimento = cidadeNascimento;
            this.estadoNascimento = estadoNascimento; 
        }
    
        public Jogador clone(){
            return new Jogador(this.id, this.nome, this.altura, this.peso, this.universidade, this.anoNascimento, this.cidadeNascimento, this.estadoNascimento);
        }
    
        public void ler(String s){
            String[] split = s.split(",");
            this.id =  Integer.parseInt(split[0]);
            this.nome = split[1];
            this.altura = Integer.parseInt(split[2]);
            this.peso = Integer.parseInt(split[3]);
            if(split[4].equals("")){
                this.universidade = "nao informado";
            }else
                this.universidade = split[4];
            this.anoNascimento = Integer.parseInt(split[5]);
            if(split.length >= 8){
                this.cidadeNascimento = split[6];
                this.estadoNascimento = split[7];
            }else{
            this.cidadeNascimento = "nao informado";
            this.estadoNascimento = "nao informado";
            }
        
        }
    
        public void imprimir(){
            String divisoria = " ## ";
            MyIO.print(divisoria + this.nome);
            MyIO.print(divisoria + this.altura);
            MyIO.print(divisoria + this.peso);
            MyIO.print(divisoria + this.anoNascimento);
            MyIO.print(divisoria + this.universidade);
            MyIO.print(divisoria + this.cidadeNascimento);
            MyIO.print(divisoria + this.estadoNascimento );
        }
    }
    public static class Celula {
        public Jogador elemento; // Elemento inserido na celula.
        public Celula prox; // Aponta a celula prox

        public Celula() {
            this(null);
        }

        public Celula(Jogador elemento) {
            this.elemento = elemento;
            this.prox = null;
          }
    }

    public static class Lista {
        private Celula primeiro;
        private Celula ultimo;
    
    
        public Lista() {
            primeiro = new Celula();
            ultimo = primeiro;
        }
    
    
        public void inserirInicio(Jogador jogador) {
            Celula tmp = new Celula(jogador);
            tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            if (primeiro == ultimo) {                 
                ultimo = tmp;
            }
            tmp = null;
        }
    
    
        public void inserirFim(Jogador jogador) {
            ultimo.prox = new Celula(jogador);
            ultimo = ultimo.prox;
        }
    
    
        public Jogador removerInicio() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            }
    
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            Jogador resp = primeiro.elemento;
            tmp.prox = null;
            tmp = null;
          
          return resp;
        }
    
    
        public Jogador removerFim() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            } 
    
          // Caminhar ate a penultima celula:
          Celula i;
          for(i = primeiro; i.prox != ultimo; i = i.prox);
    
          Jogador resp = ultimo.elemento; 
          ultimo = i; 
          i = ultimo.prox = null;
          
            return resp;
        }
    
    
       public void inserir(Jogador jogador, int pos) throws Exception {
    
          int tamanho = tamanho();
    
          if(pos < 0 || pos > tamanho){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
          } else if (pos == 0){
             inserirInicio(jogador);
          } else if (pos == tamanho){
             inserirFim(jogador);
          } else {
             // Caminhar ate a posicao anterior a insercao
             Celula i = primeiro;
             for(int j = 0; j < pos; j++, i = i.prox);
            
             Celula tmp = new Celula(jogador);
             tmp.prox = i.prox;
             i.prox = tmp;
             tmp = i = null;
          }
       }
    
    
        public Jogador remover(int pos) throws Exception {
          Jogador resp;
          int tamanho = tamanho();
    
          if (primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");
    
          } else if(pos < 0 || pos >= tamanho){
                throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
          } else if (pos == 0){
             resp = removerInicio();
          } else if (pos == tamanho - 1){
             resp = removerFim();
          } else {
               // Caminhar ate a posicao anterior a insercao
             Celula i = primeiro;
             for(int j = 0; j < pos; j++, i = i.prox);
            
             Celula tmp = i.prox;
             resp = tmp.elemento;
             i.prox = tmp.prox;
             tmp.prox = null;
             i = tmp = null;
          }
    
            return resp;
        }
    

        public void mostrar() {
            int contador = 0;
            for (Celula i = primeiro.prox; i != null; i = i.prox) {
                MyIO.print("[" + contador + "]");
                i.elemento.imprimir();
                MyIO.println(" ##");
                contador++;
            }
            
        }
    
    
       public int tamanho() {
          int tamanho = 0; 
          for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
          return tamanho;
       }
    }

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void lerCSV(String[] referencia, Jogador[] jogadores){
        int j = 0;
        
        Arq.openRead("/tmp/players.csv");               //ABRINDO O ARQUIVO CSV E PASSANDO OS DADOS PARA A LISTA REFERENCIA
        String linha = "";
        while(j <= 3922){
            linha = Arq.readLine();
            referencia[j] = linha;
            j++;
        }
        Arq.close();

        for(int i = 0; i < 3922; i++){                 //Alocando os respectivos dados dos jogadores na lista de objetos jogadores.
            jogadores[i] = new Jogador();
            jogadores[i].ler(referencia[i+1]);
        }
    }

    public static void main(String[] args)throws Exception{
        String[] entrada = new String[6000];
        String[] referencia = new String[6000];
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        Jogador[] jogadores = new Jogador[6000];         //CRIANDO ARRAY DE OBJETOS JOGADOR
        Lista lista = new Lista();                      //CRIANDO A LISTA PROPRIAMENTE DITA

        lerCSV(referencia, jogadores);

        for(int l = 0; l < numEntrada; l++){                //Inserindo na lista os jogadores na ordem do PUB.IN
            int comando = Integer.parseInt(entrada[l]);
            lista.inserirFim(jogadores[comando]);
        }
        
        int numOperacoes = MyIO.readInt();
        for(int i = 0; i < numOperacoes; i++){
            entrada[i] = MyIO.readLine();
            String[] split = entrada[i].split(" ");
            String key = split[0];
            switch (key) {
                case "II":
                    lista.inserirInicio(jogadores[Integer.parseInt(split[1])]);
                    break;
                
                case "IF":
                    lista.inserirFim(jogadores[Integer.parseInt(split[1])]);
                    break;
                    
                case "I*":
                    int pos = Integer.parseInt(split[1]);
                    lista.inserir(jogadores[Integer.parseInt(split[2])], pos);
                    break;
                    
                case "RI":
                    MyIO.print("(R)" + " ");
                    MyIO.println((lista.removerInicio()).getNome());
                    break;  
                    
                case "RF":
                    MyIO.print("(R)" + " ");
                    MyIO.println((lista.removerFim()).getNome());    
                    break;

                case "R*":
                    MyIO.print("(R)" + " ");
                    MyIO.println((lista.remover(Integer.parseInt(split[1])).getNome()));
                    break;

                default:
                    break;
            }
        }
        lista.mostrar();
        
    }
    
}
