class TP04Q01{
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

        public boolean pesquisar(String chave) {
            System.out.print(chave + " ");
            System.out.print("raiz" + " ");
            return pesquisar(chave, raiz);
        }

        private boolean pesquisar(String chave, No i) {
            boolean resp;

            if (i == null) {
               resp = false;
      
            } else if (chave.compareTo(i.elemento.nome) == 0) {
                comp++;
               resp = true;
               System.out.println("SIM");
      
            } else if (chave.compareTo(i.elemento.nome) < 0) {
                comp++;
               System.out.print("esq" + " "); 
               resp = pesquisar(chave, i.esq);
      
            } else {
               System.out.print("dir" + " "); 
               resp = pesquisar(chave, i.dir);
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
                i.elemento.imprimir();
                caminharCentral(i.dir); // Elementos da direita.
            }
        }

        public void caminharPre() {
            System.out.print("[ ");
            caminharPre(raiz);
            System.out.println("]");
        }

        private void caminharPre(No i) {
            if (i != null) {
                i.elemento.imprimir();
                caminharPre(i.esq); // Elementos da esquerda.
                caminharPre(i.dir); // Elementos da direita.
            }
        }

        public void caminharPos() {
            System.out.print("[ ");
            caminharPos(raiz);
            System.out.println("]");
        }

        private void caminharPos(No i) {
            if (i != null) {
                caminharPos(i.esq); // Elementos da esquerda.
                caminharPos(i.dir); // Elementos da direita.
                i.elemento.imprimir();
            }
        }

        public void inserir(Jogador x) throws Exception {
            raiz = inserir(x, raiz);
        }

        private No inserir(Jogador x, No i) throws Exception {
          if (i == null) {
            i = new No(x);
    
          }else if (x.nome.compareTo(i.elemento.nome) < 0) {
            comp++;
            i.esq = inserir(x, i.esq);
    
          }else if (x.nome.compareTo(i.elemento.nome) > 0) {
            comp++;
            i.dir = inserir(x, i.dir);
    
          }else {
            throw new Exception("Erro ao inserir!");
          }
    
           return i;
        }

        public void inserirPai(Jogador x) throws Exception {
            if(raiz == null){
               raiz = new No(x);

            }else if(x.nome.compareTo(raiz.elemento.nome) < 0){
                inserirPai(x, raiz.esq, raiz);

            }else if(x.nome.compareTo(raiz.elemento.nome) > 0){
                inserirPai(x, raiz.dir, raiz);

            }else {
               throw new Exception("Erro ao inserirPai!");
            }
        }

        private void inserirPai(Jogador x, No i, No pai) throws Exception {
            if (i == null) {
             if(x.nome.compareTo(i.elemento.nome) < 0){
                pai.esq = new No(x);
             } else {
                pai.dir = new No(x);
               }
            } else if (x.nome.compareTo(i.elemento.nome) < 0) {
                inserirPai(x, i.esq, i);
            }else if (x.nome.compareTo(i.elemento.nome) > 0) {
                inserirPai(x, i.dir, i);
            } else {
                throw new Exception("Erro ao inserirPai!");
            }
        }

        public void remover(Jogador x) throws Exception {
            raiz = remover(x, raiz);
        }

        private No remover(Jogador x, No i) throws Exception {
          if (i == null) {
            throw new Exception("Erro ao remover!");
    
          }else if (x.nome.compareTo(i.elemento.nome) < 0) {
            i.esq = remover(x, i.esq);
    
          }else if (x.nome.compareTo(i.elemento.nome) > 0) {
            i.dir = remover(x, i.dir);
    
          // Sem no a direita.
          }else if (i.dir == null) {
            i = i.esq;
    
          // Sem no a esquerda.
          }else if (i.esq == null) {
            i = i.dir;
    
          // No a esquerda e no a direita.
          }else {
            i.esq = antecessor(i, i.esq);
          }
    
           return i;
        }

        private No antecessor(No i, No j) {
           // Existe no a direita.
           if(j.dir != null) {
            // Caminha para direita.
            j.dir = antecessor(i, j.dir);
     
           // Encontrou o maximo da subarvore esquerda.
           }else {
               i.elemento = j.elemento; // Substitui i por j.
               j = j.esq; // Substitui j por j.ESQ.
            }
            return j;
        }

        public void remover2(Jogador x) throws Exception {
            if (raiz == null) {
               throw new Exception("Erro ao remover2!");
            }else if(x.nome.compareTo(raiz.elemento.nome) < 0){
               remover2(x, raiz.esq, raiz);
            }else if (x.nome.compareTo(raiz.elemento.nome) > 0){
               remover2(x, raiz.dir, raiz);
            }else if (raiz.dir == null) {
               raiz = raiz.esq;
            }else if (raiz.esq == null) {
               raiz = raiz.dir;
            }else {
               raiz.esq = antecessor(raiz, raiz.esq);
            }
        }

        private void remover2(Jogador x, No i, No pai) throws Exception {
           if (i == null) {
            throw new Exception("Erro ao remover2!");
            }else if (x.nome.compareTo(i.elemento.nome) < 0) {
               remover2(x, i.esq, i);
            }else if (x.nome.compareTo(i.elemento.nome) > 0) {
               remover2(x, i.dir, i);
            }else if (i.dir == null) {
               pai = i.esq;
            }else if (i.esq == null) {
               pai = i.dir;
            }else {
               i.esq = antecessor(i, i.esq);
            }
        }

        public Jogador getRaiz() throws Exception {
            return raiz.elemento;
        } 

        public  void arqLog(long tempo){
            Arq.openWrite("655938_arvoreBinaria.txt");
        
            Arq.print("655938"+'\t'+tempo+'\t'+ comp);
        
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
        ArvoreBinaria arvore = new ArvoreBinaria();

        long time = System.currentTimeMillis();
        
        for(int i = 0; i < numEntrada; i++){
            int ID = Integer.parseInt(entrada[i]);
            arvore.inserir(jogadores[ID]);
        }
        

        numEntrada = 0;                       //Resetando o numEntrada para ler o pub.in ate o segundo fim.
        boolean existe;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int l = 0; l < numEntrada; l++){
            existe = arvore.pesquisar(entrada[l]);
            if(existe == false){
                System.out.println("NAO");
            }
        }
        long time2 = System.currentTimeMillis();
        long timeFinal = time2 - time;

        arvore.arqLog(timeFinal);

    }
}
