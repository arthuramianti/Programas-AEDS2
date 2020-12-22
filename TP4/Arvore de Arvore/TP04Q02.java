class TP04Q02{
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
        public int elemento; // Conteudo do no.
        public No esq; // No da esquerda.
        public No dir; // No da direita.
        public No2 outro;
        
        public No(int elemento) {
            this.elemento = elemento;
            this.esq = this.dir = null;
            this.outro = null;
        }
    
        public No(int elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
            this.outro = null;
        }
    }

    public static class No2 {
        public String elemento; // Conteudo do no.
        public Jogador jog;
        public No2 esq; // No da esquerda.
        public No2 dir; // No da direita.
        
        public No2(String elemento, Jogador jog) {
            this.elemento = elemento;
            this.jog = jog;
            this.esq = this.dir = null;
        }
    
        public No2(String elemento, No2 esq, No2 dir, Jogador jog) {
            this.elemento = elemento;
            this.jog = jog;
            this.esq = esq;
            this.dir = dir;
        }
    }

    public static class ArvoreArvore {
        private No raiz;
        private No2 raiz2;
        public static int comp = 0;

        public ArvoreArvore()throws Exception{
            raiz = null;
            inserir(7);
            inserir(3);
            inserir(11);
            inserir(1);
            inserir(5);
            inserir(9);
            inserir(12);
            inserir(0);
            inserir(2);
            inserir(4);
            inserir(6);
            inserir(8);
            inserir(10);
            inserir(13);
            inserir(14);
        }

        public void inserir(int x) throws Exception {
            raiz = inserir(x, raiz);
        }

        private No inserir(int x, No i) throws Exception {
            if (i == null) {
                i = new No(x);
                
            }else if (x < i.elemento) {
                comp++;
                i.esq = inserir(x, i.esq);
    
            }else if (x > i.elemento) {
                comp++;
                i.dir = inserir(x, i.dir);
    
            }else {
                throw new Exception("Erro ao inserir!");
            }
    
           return i;
        }

        public void inserir2(Jogador jog, int mod){
            inserir2(jog,raiz, mod);
        }

        private void inserir2(Jogador jog, No i, int mod){
            if (i.elemento == mod){
                i.outro = inserir2(jog, i.outro, mod);

            }else if (mod < i.elemento){
                inserir2(jog, i.esq, mod);

            }else if(mod > i.elemento){
                inserir2(jog, i.dir, mod);
            }

        }

        private No2 inserir2(Jogador jog, No2 j, int mod){
            if(j == null){
                j = new No2(jog.nome, jog);

            }else if(jog.nome.compareTo(j.elemento) < 0){
                comp++;
                j.esq = inserir2(jog, j.esq, mod);

            }else if(jog.nome.compareTo(j.elemento) > 0){
                comp++;
                j.dir = inserir2(jog, j.dir, mod);
            }

            return j;
        }

    

        public void caminharCentral() {
            System.out.print("[ ");
            caminharCentral(raiz);
            System.out.println("]");
        }

        private void caminharCentral(No i) {
            if (i != null) {
                caminharCentral(i.esq); // Elementos da esquerda.
                System.out.print(i.elemento + " ");
                caminharCentral(i.dir); // Elementos da direita.
            }
        }

        public void caminharCentral2(){
            System.out.print("[ ");
            caminharCentral2(raiz2);
            System.out.print("]");
        }

        public void caminharCentral2(No2 j){
            if (j != null){
                caminharCentral2(j.esq);
                j.jog.imprimir();
                caminharCentral2(j.dir);
            }
        }

        public void caminharPre() {
            System.out.print("[ ");
            caminharPre(raiz);
            System.out.println("]");
        }

        private void caminharPre(No i) {
            if (i != null) {
                System.out.print(i.elemento + " ");
                caminharPre(i.esq); // Elementos da esquerda.
                caminharPre(i.dir); // Elementos da direita.
            }
        }

        public void caminharPre2(){
            System.out.print("[");
            caminharPre2(raiz2);
            System.out.print("]");
        }

        private void caminharPre2(No2 j){
            if (j != null){
                j.jog.imprimir();
                caminharPre2(j.esq);
                caminharPre2(j.dir);
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
                System.out.print(i.elemento + " ");
            }
        }


        public boolean pesquisar(String x, int a) {
            System.out.print(x + " raiz" + " ");
            return pesquisar(x, a %15, raiz);
        }
    
        private boolean pesquisar(String x,int mod, No i) {
            boolean resp =  false;
    
            if (i == null) {
                resp = false;
    
            }else{
                if(!resp)
                    resp = pesquisar2(x, i.outro);
    
                if(!resp){
                    System.out.print(" esq");
                    resp = pesquisar(x, mod, i.esq); 
                }if(!resp){
                System.out.print(" dir");
                resp = pesquisar(x, mod, i.dir);
                }
            }
    
            return resp;
        }

        public boolean pesquisar2(String x, No2 i){
            boolean resp = false;

            if (i == null) {
               resp = false;
    
            } else {
                if (i.elemento.equals(x)){ 
                    resp = true;
                } 

                if(!resp){
                    System.out.print(" ESQ");
                    resp = pesquisar2(x,i.esq); 
                }
                if(!resp){
                    System.out.print(" DIR");
                    resp = pesquisar2(x,i.dir); 
                }
            }

            return resp;
    
       }

       public  void arqLog(long tempo){
        Arq.openWrite("655938_avl.txt");
    
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
        ArvoreArvore arvore = new ArvoreArvore();
        ArvoreArvore arvore2 = new ArvoreArvore();

        long time = System.currentTimeMillis();
        for(int i = 0; i < numEntrada; i++){
            int ID = Integer.parseInt(entrada[i]);
            int height = jogadores[ID].altura;
            int mod = height % 15;
            arvore.inserir2(jogadores[ID], mod);
        }

        numEntrada = 0;
        boolean existe;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;
        
        for(int l = 0; l < numEntrada; l++){
            for(int m = 0; m < 3922; m++){
                if(entrada[l].equals(jogadores[m].nome)){
                    existe = arvore.pesquisar(entrada[l], jogadores[m].altura);
                    if (existe == false){
                        MyIO.println(" NAO");
                    }else{
                        MyIO.println(" SIM");
                    }
                    m = 3922;
                }
            }
        }
        long time2 = System.currentTimeMillis();
        long timefinal = time2 - time;
        arvore.arqLog(timefinal);


    }

}


