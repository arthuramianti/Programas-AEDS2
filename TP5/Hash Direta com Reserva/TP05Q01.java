class TP05Q01{
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

    public static class Hash{
        Jogador tabela[];
        int m1, m2, m, reserva;
        int NULO = -1;
        int comp = 0;
     
        public Hash (){
           this(21, 9);
        }

        public Hash (int m1, int m2){
            this.m1 = m1;
            this.m2 =  m2;
            this.m = m1 + m2;
            this.tabela = new Jogador [this.m];
            for(int i = 0; i < m; i++){
               tabela[i] = new Jogador();
            }
            reserva  = 0;
            comp = 0;
        }
      
        public int h(Jogador elemento){
            return elemento.altura % m1;
        }

        public boolean inserir (Jogador elemento){
            boolean resp = false;
      
            if(elemento.altura != 0){
      
               int pos = h(elemento);
      
                if(tabela[pos].altura == 0){
                    tabela[pos] = elemento;
                    resp = true;
      
                } else if (reserva < m2){
                    tabela[m1 + reserva] = elemento;
                    reserva++;
                    resp = true;
                }
            }
      
            return resp;
        }

        public boolean pesquisar (Jogador elemento){
            boolean resp = false;
            
            System.out.print(elemento.nome);

            int pos = h(elemento);
      
            if(tabela[pos].nome == elemento.nome){
                comp++;
                System.out.println(" SIM");
                resp = true;
      
            } else {
                for(int i = 0; i < reserva; i++){
                    if(tabela[m1 + i].nome == elemento.nome){
                        comp++;
                        System.out.println(" SIM");
                        resp = true;
                        i = reserva;
                    }
                }
            }
            return resp;
        }

        public  void arqLog(long tempo){
            Arq.openWrite("655938_hashReserva.txt");
        
            Arq.print("655938"+'\t'+tempo+'\t'+ comp);
        
            Arq.close();
            }
    }


    /*
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

        public void mostrar() {
            for (Celula i = primeiro; i.elemento != null; i = i.prox) {
                System.out.println(i.elemento.nome);
            }
            
        }

        public void inserirDif(Jogador jogador){
            ultimo.elemento = jogador;
            ultimo.prox = new Celula();
            ultimo = ultimo.prox;

        }
    }

    public static class Hash {
        Lista tabela[];
        int m1, m2, m, reserva;
        int NULO = -1;
     
        public Hash (){
           this(21, 9);
        }
     
        public Hash (int m1, int m2){
           this.m1 = m1;
           this.m2 =  m2;
           this.m = m1 + m2;
           this.tabela = new Lista [this.m];
           for(int i = 0; i < m; i++){
              tabela[i] = new Lista();
           }
           reserva  = 0;
        }
     
        public int h(Jogador elemento){
           return elemento.altura % m1;
        }
     
        public boolean inserir (Jogador elemento){
           boolean resp = false;
     
            if(elemento.altura != 0){
     
                int pos = h(elemento);
     
                if(tabela[pos].primeiro == null){
                    tabela[pos].inserirDif(elemento);
                    resp = true;
     
                } else if (reserva < m2){
                    tabela[m1 + reserva].inserirDif(elemento);
                    reserva++;
                    resp = true;
                } else{
                    tabela[pos].inserirDif(elemento);
                }
                    
                
            }
     
            return resp;
        }
     
     
        public boolean pesquisar (String elemento){
            boolean resp = false;
     
            System.out.print(elemento);

            for(int i = 0; i < m; i++){
                if(tabela[i].primeiro.elemento != null){

                    if(tabela[i].primeiro.elemento.nome.equals(elemento)){
                        //System.out.print(" " +  i);
                        System.out.println(" SIM");
                        resp = true;
                        i = 30;

                    }else if(tabela[i].primeiro.elemento.nome.compareTo(elemento) != 0 && tabela[i].primeiro.prox != null){
                        for(Celula l = tabela[i].primeiro; l.elemento != null; l = l.prox){
                            if(l.elemento.nome.equals(elemento)){
                                //System.out.print(" " + i);
                                System.out.println(" SIM");
                                resp = true;
                            }
                        }
                    }
                }
            }
     
            return resp;
        }
    }*/

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
        Hash hash = new Hash(21, 9);

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
                    aux[i] = jogadores[l];
                    l = 3922;
                }else if(l == 3921){
                    aux[i] = new Jogador();
                    aux[i].nome = entrada[i];
                }
            }
        }
        
        long time = System.currentTimeMillis();

        for(int m = 0; m < aux.length; m++){
            if(aux[m] != null){
                if(!hash.pesquisar(aux[m])){
                    System.out.println(" NAO");
                }
            }
            
        }
        hash.arqLog(System.currentTimeMillis() - time);

        

    }




}
