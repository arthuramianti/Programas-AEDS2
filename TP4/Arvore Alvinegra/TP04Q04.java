class TP04Q04{
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

    public static class NoAN{
        public boolean cor;
        public Jogador elemento;
        public NoAN esq, dir;

        public NoAN (){
            this(null);
        }

        public NoAN (Jogador elemento){
            this(elemento, false, null, null);
        }

        public NoAN (Jogador elemento, boolean cor){
            this(elemento, cor, null, null);
        }

        public NoAN (Jogador elemento, boolean cor, NoAN esq, NoAN dir){
          this.cor = cor;
          this.elemento = elemento;
          this.esq = esq;
          this.dir = dir;
        }
      }
      

    public static class Alvinegra {
        private NoAN raiz; 
        public static int comp;
    
        
        public Alvinegra() {
            raiz = null;
        }
    
       
        public boolean pesquisar(String elemento) {
            System.out.print(elemento + " raiz");
            return pesquisar(elemento, raiz);
        }
    
        private boolean pesquisar(String elemento, NoAN i) {
            boolean resp;

            if (i == null) {
                resp = false;
    
            } else if (elemento.equals(i.elemento.nome)) {
                resp = true;
    
            } else if (elemento.compareTo(i.elemento.nome) < 0) {
                System.out.print(" esq");
                resp = pesquisar(elemento, i.esq);
    
            } else {
                System.out.print(" dir");
                resp = pesquisar(elemento, i.dir);
            }

            return resp;
        }
    
        public void mostrarCentral() {
            System.out.print("[ ");
            mostrarCentral(raiz);
            System.out.println("]");
        }
    
        private void mostrarCentral(NoAN i) {
            if (i != null) {
                mostrarCentral(i.esq); // Elementos da esquerda.
                System.out.println(i.elemento.nome + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
                mostrarCentral(i.dir); // Elementos da direita.
            }
        }
    
        public void mostrarPre() {
            System.out.print("[ ");
            mostrarPre(raiz);
            System.out.println("]");
        }
    
        private void mostrarPre(NoAN i) {
            if (i != null) {
                System.out.println(i.elemento.nome + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
                mostrarPre(i.esq); // Elementos da esquerda.
                mostrarPre(i.dir); // Elementos da direita.
            }
        }
    
        public void mostrarPos() {
            System.out.print("[ ");
            mostrarPos(raiz);
            System.out.println("]");
        }
    
        private void mostrarPos(NoAN i) {
            if (i != null) {
                mostrarPos(i.esq); // Elementos da esquerda.
                mostrarPos(i.dir); // Elementos da direita.
                System.out.println(i.elemento.nome + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
            }
        }
    
    
        public void inserir(Jogador elemento) throws Exception {
       
            //Se a arvore estiver vazia
            if(raiz == null){
                raiz = new NoAN(elemento, false);
                //System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento.nome + ").");
    
            //Senao, se a arvore tiver um elemento 
            } else if (raiz.esq == null && raiz.dir == null){
                if (raiz.elemento.nome.compareTo(elemento.nome) > 0){
                    raiz.esq = new NoAN(elemento, true);
                    //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento.nome + ") e esq(" + raiz.esq.elemento.nome +").");
                } else {
                    raiz.dir = new NoAN(elemento, true);
                    //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento.nome + ") e dir(" + raiz.dir.elemento.nome +").");
                }
    
            //Senao, se a arvore tiver dois elementos (raiz e dir)
            } else if (raiz.esq == null){
    
                if(raiz.elemento.nome.compareTo(elemento.nome) > 0){
                    raiz.esq = new NoAN(elemento);
                    //System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento.nome + "), esq (" + raiz.esq.elemento.nome +") e dir(" + raiz.dir.elemento.nome +").");
    
                } else if (raiz.dir.elemento.nome.compareTo(elemento.nome) > 0){
                    raiz.esq = new NoAN(raiz.elemento);
                    raiz.elemento = elemento;
                    //System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento.nome + "), esq (" + raiz.esq.elemento.nome +") e dir(" + raiz.dir.elemento.nome +").");
    
                } else {
                    raiz.esq = new NoAN(raiz.elemento);
                    raiz.elemento = raiz.dir.elemento;
                    raiz.dir.elemento = elemento;
                    //System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento.nome + "), esq (" + raiz.esq.elemento.nome +") e dir(" + raiz.dir.elemento.nome +").");
                }
    
            raiz.esq.cor = raiz.dir.cor = false;
             
            //Senao, se a arvore tiver dois elementos (raiz e esq)
            } else if (raiz.dir == null){
             
                if(raiz.elemento.nome.compareTo(elemento.nome) < 0){
                    raiz.dir = new NoAN(elemento);
                    //System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento.nome + "), esq (" + raiz.esq.elemento.nome +") e dir(" + raiz.dir.elemento.nome +").");
                } else if (raiz.esq.elemento.nome.compareTo(elemento.nome) < 0){
                    raiz.dir = new NoAN(raiz.elemento);
                    raiz.elemento = elemento;
                    //System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento.nome + "), esq (" + raiz.esq.elemento.nome +") e dir(" + raiz.dir.elemento.nome +").");
                } else {
                    raiz.dir = new NoAN(raiz.elemento);
                    raiz.elemento = raiz.esq.elemento;
                    raiz.esq.elemento = elemento;
                    //System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento.nome + "), esq (" + raiz.esq.elemento.nome +") e dir(" + raiz.dir.elemento.nome +").");
                }
    
            raiz.esq.cor = raiz.dir.cor = false;
    
            //Senao, a arvore tem tres ou mais elementos
            } else {
                //System.out.println("Arvore com tres ou mais elementos...");
                inserir(elemento, null, null, null, raiz);
            }
    
            raiz.cor = false;
        }
    
        private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i){
    
            //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
            if(pai.cor == true){
    
                //4 tipos de reequilibrios e acoplamento
                if(pai.elemento.nome.compareTo(avo.elemento.nome) > 0){      // rotacao a esquerda ou direita-esquerda
                    if(i.elemento.nome.compareTo(pai.elemento.nome) > 0){
                        avo = rotacaoEsq(avo);
                    } else {
                        avo = rotacaoDirEsq(avo);
                    }
    
                } else { // rotacao a direita ou esquerda-direita
                    if(i.elemento.nome.compareTo(pai.elemento.nome) < 0){
                        avo = rotacaoDir(avo);
                    } else {
                        avo = rotacaoEsqDir(avo);
                    }
                }
    
                if (bisavo == null){
                    raiz = avo;
                } else {
                    if(avo.elemento.nome.compareTo(bisavo.elemento.nome) < 0){
                        bisavo.esq = avo;
                    } else {
                        bisavo.dir = avo;
                    }
                }
    
                //reestabelecer as cores apos a rotacao
                avo.cor = false;
                avo.esq.cor = avo.dir.cor = true;
                //System.out.println("Reestabeler cores: avo(" + avo.elemento.nome + "->branco) e avo.esq / avo.dir(" + avo.esq.elemento.nome + "," + avo.dir.elemento.nome + "-> pretos)");
            }   //if(pai.cor == true)
        }
    
        
        private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
            if (i == null) {
    
                if(elemento.nome.compareTo(pai.elemento.nome) < 0){
                    comp++;
                    i = pai.esq = new NoAN(elemento, true);
                } else {
                    i = pai.dir = new NoAN(elemento, true);
                }
    
                if(pai.cor == true){
                    balancear(bisavo, avo, pai, i);
                }
    
            } else {
    
                //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
                if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
                    i.cor = true;
                    i.esq.cor = i.dir.cor = false;

                    if(i == raiz){
                        i.cor = false;
                    }else if(pai.cor == true){
                        balancear(bisavo, avo, pai, i);
                    }
                }
                if (elemento.nome.compareTo(i.elemento.nome) < 0) {
                    comp++;
                    inserir(elemento, avo, pai, i, i.esq);
                } else if (elemento.nome.compareTo(i.elemento.nome) > 0) {
                    comp++;
                    inserir(elemento, avo, pai, i, i.dir);
                } else {
                    throw new Exception("Erro inserir (elemento repetido)!");
                }
            }
        }
    
        private NoAN rotacaoDir(NoAN no) {
            //System.out.println("Rotacao DIR(" + no.elemento.nome + ")");
            NoAN noEsq = no.esq;
            NoAN noEsqDir = noEsq.dir;

            noEsq.dir = no;
            no.esq = noEsqDir;

            return noEsq;
        }
    
        private NoAN rotacaoEsq(NoAN no) {
            //System.out.println("Rotacao ESQ(" + no.elemento.nome + ")");
            NoAN noDir = no.dir;
            NoAN noDirEsq = noDir.esq;
            
            noDir.esq = no;
            no.dir = noDirEsq;
            return noDir;
        }
    
        private NoAN rotacaoDirEsq(NoAN no) {
            no.dir = rotacaoDir(no.dir);
            return rotacaoEsq(no);
        }
    
        private NoAN rotacaoEsqDir(NoAN no) {
            no.esq = rotacaoEsq(no.esq);
            return rotacaoDir(no);
        }

        public  void arqLog(long tempo){
            Arq.openWrite("655938_avinegra.txt");
        
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
        Alvinegra arvore = new Alvinegra();
        
        long time = System.currentTimeMillis();
        for(int i = 0; i < numEntrada; i++){
            int ID = Integer.parseInt(entrada[i]);
            arvore.inserir(jogadores[ID]);
        }

        numEntrada = 0;
        boolean existe;

        do{
            entrada[numEntrada] =  MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int l = 0; l < numEntrada; l++){
            existe = arvore.pesquisar(entrada[l]);
            if(existe == true){
                System.out.println(" SIM");
            }else{
                System.out.println(" NAO");
            }
        }
        long time2 = System.currentTimeMillis() - time;
        arvore.arqLog(time2);
    
    }

}



