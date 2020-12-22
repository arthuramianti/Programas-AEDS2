class TP05Q04{
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
            MyIO.print(divisoria + this.id);
            MyIO.print(divisoria + this.nome);
            MyIO.print(divisoria + this.altura);
            MyIO.print(divisoria + this.peso);
            MyIO.print(divisoria + this.anoNascimento);
            MyIO.print(divisoria + this.universidade);
            MyIO.print(divisoria + this.cidadeNascimento);
            MyIO.println(divisoria + this.estadoNascimento );
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

        public void inserirFim(Jogador jogador) {
            ultimo.prox = new Celula(jogador);
            ultimo = ultimo.prox;
        }

        public void mostrar() {
            int contador = 0;
            for (Celula i = primeiro.prox; i != null; i = i.prox) {
                MyIO.print("[" + contador + "]");
                MyIO.print(i.elemento.nome);
                MyIO.println(" ##");
                contador++;
            }
            
        }

        public boolean pesquisar(Jogador elemento){
            boolean resp = false;
            for(Celula i = primeiro.prox; i != null; i = i.prox){
                if(i.elemento.nome == elemento.nome){
                    resp = true;
                    System.out.println(" SIM");
                    i = ultimo;
                }
            }
            return resp;
        }

    }    

    public static class No{
        public Jogador elemento; // Conteudo do no.
        public No esq, dir;  // Filhos da esq e dir.
        
        public No(Jogador elemento) {
            this(elemento, null, null);
        }

        public No(Jogador elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    public static class ArvoreBinaria{
        private No raiz;         //Raiz da arvore
        public static int comp = 0;

        public ArvoreBinaria() {
            raiz = null;
        }

        public boolean pesquisar(Jogador elemento) {
            System.out.print(" raiz");
            return pesquisar(elemento, raiz);
        }

        private boolean pesquisar(Jogador elemento, No i) {
            boolean resp = false;

            if (i == null) {
               resp = false;
      
            } else if (elemento.nome.compareTo(i.elemento.nome) == 0) {
               resp = true;
               System.out.println(" SIM");
      
            } else if (elemento.nome.compareTo(i.elemento.nome) < 0) { 
                System.out.print(" esq");
               resp = pesquisar(elemento, i.esq);
      
            } else { 
                System.out.print(" dir");
               resp = pesquisar(elemento, i.dir);
            }

            return resp;
        }

        public void caminharCentral() {
            System.out.print("[ ");
            caminharCentral(raiz);
            System.out.println("]");
        }

        private void caminharCentral(No i) {
            if (i != null) {
                caminharCentral(i.esq); // Elementos da esquerda.
                MyIO.println(i.elemento.nome);
                caminharCentral(i.dir); // Elementos da direita.
            }
        }

        public void inserir(Jogador x) throws Exception {
            raiz = inserir(x, raiz);
        }

        private No inserir(Jogador x, No i) throws Exception {
          if (i == null) {
            i = new No(x);
    
          }else if (x.nome.compareTo(i.elemento.nome) < 0) {
            i.esq = inserir(x, i.esq);
    
          }else if (x.nome.compareTo(i.elemento.nome) > 0) {
            i.dir = inserir(x, i.dir);
    
          }else {
            throw new Exception("Erro ao inserir!");
          }
    
           return i;
        }

    }


    public static class Hash{
        Jogador tabelaT1[];
        Jogador tabelaT2[];
        int m1, m2, m3, m, reserva;
        int NULO = -1;
        int comp = 0;

        Lista lista;
        ArvoreBinaria arvore;

     
        public Hash (){
           this(11, 3, 9);
        }

        public Hash (int m1, int m2, int m3){
            this.m1 = m1;
            this.m2 =  m2;
            this.m3 = m3;

            this.m = m1 + m2;

            this.tabelaT1 = new Jogador [this.m];
            this.tabelaT2 = new Jogador [m3];
            lista = new Lista();
            arvore = new ArvoreBinaria();

            for(int i = 0; i < m; i++){
               tabelaT1[i] = new Jogador();
            }

            for(int i = 0; i < m3; i++){
                tabelaT2[i] = new Jogador();
            }

            reserva  = 0;
            comp = 0;
        }
      
        public int h(Jogador elemento){
            return elemento.altura % m1;
        }

        public int hRes(Jogador elemento){
            return elemento.altura % 3;
        }

        public int h2(Jogador elemento){
            return elemento.altura % m3;
        }

        public int h2Reh(Jogador elemento){
            return ++elemento.altura % m3;
        }

        public boolean inserir (Jogador elemento) throws Exception{
            boolean resp = false;
            
      
            if(elemento.altura != 0){
      
               int pos = h(elemento);
      
                if(tabelaT1[pos].altura == 0){
                    tabelaT1[pos] = elemento;
                    resp = true;
      
                } else if (hRes(elemento) == 0 && resp == false){
                    
                    pos = h2(elemento);

                    if(tabelaT2[pos].altura == 0){
                        resp = true;
                        tabelaT2[pos] = elemento;
                    } else {
                        pos = h2Reh(elemento);
                        elemento.altura--;

                        if(tabelaT2[pos].altura == 0){
                            tabelaT2[pos] = elemento;
                            resp = true;
                        } 
                            
                    }
                }  
                if (hRes(elemento) == 1 && resp == false){
                    lista.inserirFim(elemento);
                } if(hRes(elemento) == 2 && resp == false) {
                    arvore.inserir(elemento);
                }
            }
      
            return resp;
        }

        public boolean pesquisar (Jogador elemento){
            boolean resp = false;
            
            int pos = h(elemento);
            
            if(tabelaT1[pos].nome == elemento.nome){
                System.out.println(" SIM");
                resp = true;
      
            } if(resp == false){

                pos = h2(elemento);

                    if(tabelaT2[pos].nome == elemento.nome){
                        System.out.println(" SIM");
                        resp = true;
                    } else {
                        pos = h2Reh(elemento);

                        if(tabelaT2[pos].nome == elemento.nome){
                            System.out.println(" SIM");
                            resp = true;
                        } 
                    }
                }  if(resp == false){
                    resp = lista.pesquisar(elemento);
    
                } if(resp == false) {
                    resp = arvore.pesquisar(elemento);
                }

            return resp;
        }

        public void mostrar(){
            MyIO.println("------TABLEA T1-------");
            for(int i = 0; i < this.m; i++){
                MyIO.print(i);
                MyIO.println(tabelaT1[i].nome);
            }
            MyIO.println("-----TABELA T2-----");
            for(int i = 0; i < this.m3; i++){
                MyIO.print(i);
                MyIO.println(tabelaT2[i].nome);
            }
            MyIO.println("-----LISTA-----");
            lista.mostrar();
            MyIO.println("-----ARVORE-----");
            arvore.caminharCentral();
        }

        /*public  void arqLog(long tempo){
            Arq.openWrite("655938_hashReserva.txt");
        
            Arq.print("655938"+'\t'+tempo+'\t'+ comp);
        
            Arq.close();
        }*/
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

    public static void main(String[] args) throws Exception{
        String[] entrada = new String[6000];
        String[] referencia = new String[6000];
        Jogador[] jogadores = new Jogador[6000];         //CRIANDO ARRAY DE OBJETOS JOGADOR
        Jogador[] aux = new Jogador[1000];            //Array para instanciar os nomes dos jogadores da segunda parte do pub.in
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        lerCSV(referencia, jogadores);
        Hash hash = new Hash(11, 3, 9);

        for(int i = 0; i < numEntrada; i++){
            int ID = Integer.parseInt(entrada[i]);
            hash.inserir(jogadores[ID]);
        }
        
        //SEGUNDA PARTE DO PUB.IN
        numEntrada = 0;
        

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            for(int l = 0; l < 3922; l++){
                if(entrada[i].equals(jogadores[l].nome)){
                    System.out.print(jogadores[l].nome);
                    if(!hash.pesquisar(jogadores[l])){
                         System.out.println(" NAO");
                    }
                    l = 3922;
                }else if(l == 3921){
                    System.out.print(entrada[i]);
                    if(!hash.pesquisar(jogadores[2011])){
                         System.out.println(" NAO");
                    }
                }
            }
        }
        
        long time = System.currentTimeMillis();

        /*for(int m = 0; m < aux.length; m++){
            if(aux[m] != null){
                System.out.print(aux[m].nome);
                if(!hash.pesquisar(aux[m])){
                   System.out.println(" NAO");
                }
            }
            
        }*/
        //hash.arqLog(System.currentTimeMillis() - time);

        

    }

}
