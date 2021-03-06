class TP02Q18{
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
       Arq.openWrite("matricula_counting.txt");
       Arq.print("Matricula:655938" + "\t" + "Comparaçoes:" + numCompara + "\t" + "Movimentaçoes:" + numMovi + "\t" + "Tempo de Execuçao (ns):" + timeFim);
       Arq.close();
    }  
    
    public static void sort(Jogador[] jogador, int numRegistros) {
        quickSort(0, numRegistros-1, jogador);
    }

    public static void quickSort(int esq, int dir, Jogador[] jogador){
      int i = esq, j = dir;
      Jogador pivo = jogador[(dir+esq)/2];
      while (i <= j) {
         while (jogador[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0) i++;
         while (jogador[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0) j--;
         if (i <= j) {
            swap(i, j, jogador);
            i++;
            j--;
         }
      }
      if (esq < j)  quickSort(esq, j, jogador);
      if (i < dir)  quickSort(i, dir, jogador);
   }

   public static void swap(int a, int b, Jogador[] jogador){
      Jogador aux = jogador[a];
      jogador[a] = jogador[b];
      jogador[b] = aux;
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
      sort(jogador, numRegistros);

      for (int i = (numRegistros - 1); i > 0; i--) {
			for (int m = 0; m < i; m++) {
				if (jogador[m].getEstadoNascimento().equals(jogador[m + 1].getEstadoNascimento()) && jogador[m].getNome().compareTo(jogador[m+1].getNome()) > 0) {
               Jogador aux = jogador[m];
               jogador[m] = jogador[m+1];
               jogador[m+1] = aux;

				}
			}
      }
      
      for(int l = 0; l < 10; l++){
         jogador[l].imprimir();
      }

   }
 
 
 }
 
