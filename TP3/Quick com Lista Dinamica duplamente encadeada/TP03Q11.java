class TP03Q11{
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

    public static class CelulaDupla{
        public Jogador elemento;
	    public CelulaDupla ant;
        public CelulaDupla prox;
        int index;

	    public CelulaDupla() {
		    this(null);
	    }


	    public CelulaDupla(Jogador elemento) {
		    this.elemento = elemento;
		    this.ant = this.prox = null;
        }
    
    }

    public static class ListaDupla{
        private CelulaDupla primeiro;
        private CelulaDupla ultimo;
        
        
        public ListaDupla() {
            primeiro = new CelulaDupla();
            ultimo = primeiro;
        }


        public void inserirFim(Jogador x, int pos) {
            ultimo.prox = new CelulaDupla(x);
            ultimo.prox.ant = ultimo;
            ultimo = ultimo.prox;
            ultimo.index = pos;
        }

        /*public void inserir(Jogador x, int pos) throws Exception {
            int tamanho = tamanho();
      
            if(pos < 0 || pos > tamanho){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
            } else if (pos == 0){
               inserirInicio(x);
            } else if (pos == tamanho){
               inserirFim(x);
            } else {
               CelulaDupla i = primeiro;
               for(int j = 0; j < pos; j++, i = i.prox);
              
               CelulaDupla tmp = new CelulaDupla(x);
               tmp.ant = i;
               tmp.prox = i.prox;
               tmp.ant.prox = tmp.prox.ant = tmp;
               tmp = i = null;
            }
        }*/

        public int tamanho() {
            int tamanho = 0; 
            for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
            return tamanho;
        }

        public void mostrar(){
		    for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			(i.elemento).imprimir();
            }
        }
		
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
    
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void sort(int numOperacoes) {
        quickSort(0, numOperacoes-1);
    }

    public static void quickSort(int esq, int dir){
        int posi = esq, posj = dir;
        CelulaDupla i = primeiro.prox;
    
        int x;
        for(x = 0; x < ((dir+esq)/2); x++, i = i.prox);
             Jogador pivo = i.elemento;
    
        i = primeiro.prox;
        for(x = 0; x < esq; x++, i = i.prox);
        
        CelulaDupla j = ultimo;
        for(x = tamanho()-1; x > dir; x--, j = j.ant);
    
        while (posi <= posj) {
            while (i.elemento.estadoNascimento.compareTo(pivo.estadoNascimento)  < 0 || i.elemento.estadoNascimento.compareTo(pivo.estadoNascimento) == 0 && i.elemento.nome.compareTo(pivo.nome) < 0){ i = i.prox; posi++;comp++;}
            while (j.elemento.estadoNascimento.compareTo(pivo.estadoNascimento) > 0 || j.elemento.estadoNascimento.compareTo(pivo.estadoNascimento) == 0 && j.elemento.nome.compareTo(pivo.nome) > 0){ j = j.ant; posj--;comp++;}
            if (posi <= posj) {
                swap(i, j);
                i = i.prox;
                j = j.ant;
                posi++;
                posj--;
            }
        }
        if (esq < posj)  
            quicksort(esq, posj);
        if (posi < dir)  
            quicksort(posi, dir);
    }

   public static void swap(int a, int b, Jogador[] jogador){
      Jogador aux = jogador[a];
      jogador[a] = jogador[b];
      jogador[b] = aux;
   }



    public static void main(String[] args){
        String[] entrada = new String[6000];
        String[] referencia = new String[6000];
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        Jogador[] jogadores = new Jogador[6000];         //CRIANDO ARRAY DE OBJETOS JOGADOR
        ListaDupla lista = new ListaDupla();                      //CRIANDO A LISTA PROPRIAMENTE DITA

        lerCSV(referencia, jogadores);

        for(int l = 0; l < numEntrada; l++){                //Inserindo na lista os jogadores na ordem do PUB.IN
            int comando = Integer.parseInt(entrada[l]);
            lista.inserirFim(jogadores[comando], l);
        }
        sort(numEntrada);

        lista.mostrar();
    }
}
