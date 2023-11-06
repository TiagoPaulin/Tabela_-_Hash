public class HashTable {
    // definindo atributos
    private Registro[] hashTable;
    private int tamanho;
    private long totalColisoes;
    private long totalComparacoes;
    // metodo contrutor
    public HashTable(int tamanho){
        this.tamanho = tamanho;
        this.hashTable = new Registro[tamanho];
        this.totalColisoes = 0;
        this.totalComparacoes = 0;
    }
    // metodo para inserir na tabela
    public void inserir(Integer key, int tipoHash){ // recebe a chave e o tipo de hash que será feito na inserção
        Registro registro = new Registro(); // cria um novo registro
        registro.setKey(key); // armazena o valor no registro
        int hash; // atributo que vai armazenar o valor de hash correspondente a chave recebida
        int colisoes = 0; // atributo que vai contabilizar o número de colisões caso a posição hash ja esteja ocupada com um registro
        if(tipoHash == 1){ // se o tipo do hash for 1
            hash = hashResto(key); // o valor do hash será calculado baseado no resto da divisão
        } else if(tipoHash == 2){ // se o tipo do hash for 2
            hash = hashMultiplicacao(key); // o valor do hash será calculado baseado na multiplicação pelo fator A
        } else { // se o tipo do hash for 3
            hash = hashDobramento(key); // o valor do hash será calculado por meio de dobramento
        }
        if(this.hashTable[hash] == null){ // após o hash ser calculado será verificado se a posição correspondente na tabela já está ocupada por um registro
           hashTable[hash] = registro; // caso esteja vazia aquela posição irá receber o registro
        } else { // caso a posição já esteja ocupada por um registro
            // o tratamento de colisões será feito por meio de encadeamento (LinkedList)
            Registro temp = hashTable[hash]; // armazena o primeiro registro em uma variável auxiliar
            colisoes ++; // contabiliza uma colisão
            if(temp.getKey() >= key){ // caso o valor do registro que ja estava ocupando a posição for menor ou igual o registro que está sendo inserido
                registro.setNext(temp); // o registro que estava ocupando a posição será setado como próximo em relação ao registro que está sendo inserido
                hashTable[hash] = registro; // o novo registro ocupa a posição do registro que estava aqui anteriormente
            } else { // caso o valor do registro que já eseja ocupando a posição seja maior que o valor que está entando
                Registro atual = temp; // cria um registro para percorrer os registros da posição
                while((atual.getNext() != null) && (atual.getNext().getKey() < key)){ // enquanto o proximo nao for nulo e o proximo for maior que a chave
                    atual = atual.getNext(); // percorre a linkedlist
                    colisoes ++; // contabilisa uma colisão a cada registro que é comparado com a chave
                }
                // após encontrar o registro que ira setar o novo registro como proximo
                if(atual.getNext() == null){ // verifico se já existe algum registro setado como próximo dele
                    atual.setNext(registro); // caso não eu seto meu novo registro
                } else { // caso tenha
                    registro.setNext(atual.getNext()); // seto como proximo do novo registro o proximo do registro que irá receber o novo
                    atual.setNext(registro); // e seto meu novo registro como próximo do no, dessa forma meu registro fica entre os dois registros mantendo a ordenação crescente
                }
            }
        }
        this.totalColisoes += colisoes; // soma as coliões registradas na inserção do registro ao total de colisões
        // feedback da inserção no terminal (comentado para não atrapalhar análises com grandes quantidades de elementos)
//        System.out.println("Código " + key + " inserido na Tabela Hash.");
//        System.out.println("Número de colisões registradas: " + colisoes);
    }
    // metodo para  buscar chave na tabela hash
    public Integer buscaHashResto(int key){ // recebe a chave que será buscada na tabela
        int hash = hashResto(key); // calcula o hash da chave
        if(hashTable[hash].getKey() == key){ // se a chave se encontra na primeira posição da tabela
            this.totalComparacoes ++; // contabiliza uma comparação
            return hashTable[hash].getKey(); // retorna o valor do registro
        } else { // caso a chave não se encontre na primeira posição da tabela
            Registro atual = hashTable[hash]; // atributo auxiliar para varrer a lista ligada
            while(atual.getKey() != key){ // enquanto o valor do registro não corresponder com a chave buscada
                atual = atual.getNext(); // percorre a lista ligada
                this.totalComparacoes ++; // contabiliza uma comparação
            }
            return atual.getKey(); // retona o valor do registro assim que for encontrado
        }
    }
    public Integer buscaHashMultiplicacao(int key){ // recebe a chave que será  buscada na tabela
        int hash = hashMultiplicacao(key); // calcula o hash da chave que está sendo buscada
        if(hashTable[hash].getKey() == key){ // se a chave corresponde ao valor do registro na primeira posição
            this.totalComparacoes ++; // contabiliza uma comparação
            return hashTable[hash].getKey(); // retorna o valor do registro
        } else { // se nao
            Registro atual = hashTable[hash]; // atributo auxiliar para percorrer a linkedlist
            while(atual.getKey() != key){ // enquanto o valor do registro não corresponder a chave buscada
                atual = atual.getNext(); // percorre a lista
                this.totalComparacoes ++; // contabiliza uma comparação
            }
            return atual.getKey(); // retorna o valor do registro assim que encontrar
        }
    }
    public Integer buscaHashDobramento(int key){ // recebe a chave que será buscada na tabela
        int hash = hashDobramento(key); // calcula o hash da chave
        if(hashTable[hash].getKey() == key){ // se corresponde a primeira posição
            this.totalComparacoes ++; // contabiliza uma comparação
            return hashTable[hash].getKey(); // retorna o valor do registro
        } else { // se nao
            Registro atual = hashTable[hash]; // atributo auxiliar para varrer os registros
            while(atual.getKey() != key){ // enquanto o valor do registro não corresponder a chave buscada
                atual = atual.getNext(); // percorre a lista
                this.totalComparacoes ++; // contabiliza uma comparação
            }
            return atual.getKey(); // retona o valor do registro assim que for encontrado
        }
    }
    // hash de resto de divisão
    public Integer hashResto(Integer key){ // recebe a chave que para calcula o hash
        return key % this.tamanho; // retorna o valor do hash da chave, que corresponde ao resto da divisão pelo tamanho da tabela
    }
    // Hash de multiplicação
    public Integer hashMultiplicacao(Integer key){ // recebe a chave para calcular o hash
        double a = 0.6180339887; // fator de multiplicação que corresponde ao valor aproximado de raíz de 5 menos 1 dividido por 2
        double value = key * a; // multiplica o chave pelo fator de multiplicação escolhido
        double fracao = value - (int) value; // pega somente a parte fracionária da operação
        return (int) (fracao * this.tamanho); // retorna o hash da chave que corresponde a parte fracionária da operação multiplicada pelo tamanho da tabela
    }
    // Hash de dobramento
    public Integer hashDobramento(Integer key){ // recebe a chave para ser calculado o hash
        int chave = key; // atributo de apoio que recebe a chave
        int a = chave % 100000; // atributo a pega os ultimos 5 digitos da chave
        chave = chave / 100000; // atualiza a chave sem os valores que foram para a
        return (a + chave) % this.tamanho; // retorna o hash que é o resto da divisão entre a soma das partes e o tamanho da tabela
    }
    // metodos get
    public long getTotalComparacoes() {
        return totalComparacoes;
    }
    public long getTotalColisoes() {
        return totalColisoes;
    }
    public Registro[] getHashTable() {
        return hashTable;
    }


}
