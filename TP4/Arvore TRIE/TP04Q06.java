class TP04Q06{
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

    public static class No {
        public char elemento;
        public int tamanho = 255;
        public No[] prox;
        public boolean folha;
        
        public No (){
           this(' ');
        }
     
        public No (char elemento){
           this.elemento = elemento;
           prox = new No [tamanho];
           for (int i = 0; i < tamanho; i++) prox[i] = null;
           folha = false;
        }
     
        public static int hash (char x){
           return (int)x;
        }
    }

    public static class ArvoreTrie {
        private No raiz;
    
        public ArvoreTrie(){
            raiz = new No();
        }
    
        public void inserir(String s) throws Exception {
            inserir(s, raiz, 0);
        }
    
        private void inserir(String s, No no, int i) throws Exception {
            //System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
            if(no.prox[s.charAt(i)] == null){
               // System.out.print("--> criando filho(" + s.charAt(i) + ")");
                no.prox[s.charAt(i)] = new No(s.charAt(i));
    
                if(i == s.length() - 1){
                    //System.out.print("(folha)");
                    no.prox[s.charAt(i)].folha = true;
                }else{
                    inserir(s, no.prox[s.charAt(i)], i + 1);
                }
    
            } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
                inserir(s, no.prox[s.charAt(i)], i + 1);
    
            } else {
                throw new Exception("Erro ao inserir!");
            } 
        }
    
    
        public boolean pesquisar(String s) throws Exception {
            return pesquisar(s, raiz, 0);
        }
    
        public boolean pesquisar(String s, No no, int i) throws Exception {
            boolean resp;
            if(no.prox[s.charAt(i)] == null){
                resp = false;
            } else if(i == s.length() - 1){
                resp = (no.prox[s.charAt(i)].folha == true);
            } else if(i < s.length() - 1 ){
                resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
            } else {
                throw new Exception("Erro ao pesquisar!");
            }
            return resp;
        }
    
    
        public void mostrar(){
            mostrar("", raiz);
        }
    
        public void mostrar(String s, No no) {
            if(no.folha == true){
                System.out.println("Palavra: " + (s + no.elemento));
            } else {
                for(int i = 0; i < no.prox.length; i++){
                    if(no.prox[i] != null){
                        //System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                        mostrar(s + no.elemento, no.prox[i]);
                    }
                }
            }
        }

        public  void arqLog(long tempo){
            Arq.openWrite("655938_arvoreTrie.txt");
        
            Arq.print("655938"+'\t'+tempo+'\t'+ "0");
        
            Arq.close();
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

    public static void main(String[] args) throws Exception{
        String[] entrada = new String[6000];
        String[] referencia = new String[6000];
        Jogador[] jogadores = new Jogador[6000];         //CRIANDO ARRAY DE OBJETOS JOGADOR
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        lerCSV(referencia, jogadores);
        ArvoreTrie arvore = new ArvoreTrie();
        
        long time = System.currentTimeMillis();
        for(int i = 0; i < numEntrada; i++){
            int ID = Integer.parseInt(entrada[i]);
            System.out.println(entrada[i] + "\t" + jogadores[ID].nome);
            arvore.inserir(jogadores[ID].nome);
        }

        numEntrada = 0;
        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        long tempo = System.currentTimeMillis();
        for(int i = 0; i < numEntrada; i++){
            int ID = Integer.parseInt(entrada[i]);
            if(arvore.pesquisar(jogadores[ID].nome) == false){
                System.out.println(entrada[i] + "\t" + jogadores[ID].nome);
                arvore.inserir(jogadores[ID].nome);
            }
            
        }
        numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            System.out.print(entrada[i]);
            if(arvore.pesquisar(entrada[i]) == true){
                System.out.println(" SIM");
            }else
                System.out.println(" NAO");
        }
        long tempofinal = System.currentTimeMillis() - tempo;
        arvore.arqLog(tempofinal);

    }

}
