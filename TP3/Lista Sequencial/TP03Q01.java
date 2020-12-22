class TP03Q01{
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

    public static class Lista{
        private Jogador[] array;
        private int n;

        public Lista () {
            this(1000);
        }

        public Lista (int tamanho){
            array = new Jogador[tamanho];
            n = 0;
        }
   
        public void inserirInicio(Jogador jogador) throws Exception {
            if(n >= array.length){
            throw new Exception("Erro ao inserir!");
        } 
        for(int i = n; i > 0; i--){
            array[i] = array[i-1];
        }

        array[0] = jogador;
        n++;
        }

        public void inserirFim(Jogador jogador) throws Exception {
            if(n >= array.length){
            throw new Exception("Erro ao inserir!");
        }

        array[n] = jogador;
        n++;
        }

        public void inserir(Jogador jogador, int pos) throws Exception {
            if(n >= array.length || pos < 0 || pos > n){
            throw new Exception("Erro ao inserir!");
        }
        for(int i = n; i > pos; i--){
            array[i] = array[i-1];
        }

        array[pos] = jogador;
        n++;
        }

        public Jogador removerInicio() throws Exception {
            if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Jogador resp = array[0];
        n--;

        for(int i = 0; i < n; i++){
            array[i] = array[i+1];
        }

        return resp;
        }

        public Jogador removerFim() throws Exception {
            if (n == 0) {
            throw new Exception("Erro ao remover!");
        }
        return array[--n];
        }

        public Jogador remover(int pos) throws Exception {
            if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        Jogador resp = array[pos];
        n--;

        for(int i = pos; i < n; i++){
         array[i] = array[i+1];
        }

        return resp;
        }

        public void mostrar (){
            for(int i = 0; i < n; i++){
                MyIO.print("[" + i + "]");
                array[i].imprimir();
                MyIO.println(" ##");
            }
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
    
