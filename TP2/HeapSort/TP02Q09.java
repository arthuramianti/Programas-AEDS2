class TP02Q09{
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
         MyIO.print("[");
         MyIO.print(this.id);
         MyIO.print(divisoria + this.nome);
         MyIO.print(divisoria + this.altura);
         MyIO.print(divisoria + this.peso);
         MyIO.print(divisoria + this.anoNascimento);
         MyIO.print(divisoria + this.universidade);
         MyIO.print(divisoria + this.cidadeNascimento);
         MyIO.println(divisoria + this.estadoNascimento + "]");
      }
   }

   public static boolean isFim(String s){
      return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   public static void arquivoLog(int numCompara, int numMovi, long timeFim){
      Arq.openWrite("matricula_heap.txt");
      Arq.print("Matricula:655938" + "\t" + "Comparaçoes:" + numCompara + "\t" + "Movimentaçoes:" + numMovi + "\t" + "Tempo de Execuçao (ns):" + timeFim);
      Arq.close();
   }  

  

   public static void swap(int a, int b, Jogador[] jogador){
      Jogador aux = jogador[a];
      jogador[a] = jogador[b];
      jogador[b] = aux;
   }

   public static void construir(int tamHeap, Jogador[] jogador){
      for(int i = tamHeap; i > 1 && jogador[i].getAltura() > jogador[i/2].getAltura(); i /= 2){
         swap(i, i/2, jogador);
      }
   }

   public static int getMaiorFilho(int i, int tamHeap, Jogador[] jogador){
      int filho;
      if (2*i == tamHeap || jogador[2*i].getAltura() > jogador[2*i+1].getAltura()){
         filho = 2*i;
      } else {
         filho = 2*i + 1;
      }

      return filho;
   }

   public static void reconstruir(int tamHeap, Jogador[] jogador){
      int i = 1;
      while(i <= (tamHeap/2)){
      int filho = getMaiorFilho(i, tamHeap, jogador);
         if(jogador[i].getAltura() < jogador[filho].getAltura()){
            swap(i, filho, jogador);
            i = filho;
         }else{
            i = tamHeap;
         }
      }
   }

   public static Jogador[] sort(Jogador[] jogador, int numRegistros) {
      //Alterar o vetor ignorando a posicao zero
      Jogador[] tmp = new Jogador[numRegistros+1];
      for(int i = 0; i < numRegistros; i++){
         tmp[i+1] = jogador[i];
      }
      jogador = tmp;

      //Contrucao do heap
      for(int tamHeap = 2; tamHeap <= numRegistros; tamHeap++){
         construir(tamHeap, jogador);
      }

      //Ordenacao propriamente dita
      int tamHeap = numRegistros;
      while(tamHeap > 1){
         swap(1, tamHeap--, jogador);
         reconstruir(tamHeap, jogador);
      }

      tmp = jogador;
      jogador = new Jogador[numRegistros];
      for(int i = 0; i < numRegistros; i++){
         jogador[i] = tmp[i+1];
      }
      return jogador;
   }

   

   public static void main(String[] args){
     String[] entrada = new String[10000];
     String[] referencia = new String[6000];
     int j = 0;
     int numEntrada = 0;
     int numRegistros = 0;
   
   do{
      entrada[numEntrada] = MyIO.readLine();
   }while(isFim(entrada[numEntrada++]) == false);
     numEntrada--;
     
     Arq.openRead("/tmp/players.csv");               //ABRINDO O ARQUIVO CSV E PASSANDO OS DADOS PARA A LISTA REFERENCIA
     String linha = "";
      while(j <= 3922){
      linha = Arq.readLine();
      referencia[j] = linha;
      j++;
      }
     Arq.close();
     
     Jogador[] jogador = new Jogador[6000];

      for(int i = 0; i < numEntrada; i++){
         int ID = Integer.parseInt(entrada[i]);
         jogador[i] = new Jogador();
         jogador[i].ler(referencia[ID + 1]);
         numRegistros++;
      }
      jogador = sort(jogador, numRegistros);

      for (int i = (numEntrada - 1); i > 0; i--) {
         for (int k = 0; k < i; k++) {
            if (jogador[k].getNome().compareTo(jogador[k + 1].getNome()) > 0 && jogador[k].getAltura() == jogador[k + 1].getAltura()) {
               Jogador aux = jogador[k];
               jogador[k]=jogador[k+1];
               jogador[k+1]=aux;
            }
         }
      }

      for(int l = 0; l < numEntrada; l++){
         jogador[l].imprimir();
      }

   }


}
