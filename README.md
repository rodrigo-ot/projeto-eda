# Repositório de Projeto de Ordenação de Algoritmos 
Este repositório contém a implementação dos seguintes algoritmos de ordenação em Java: Counting Sort, Merge Sort, Quick Sort, Selection Sort, Insertion Sort e Heap Sort. Todos os algoritmos foram implementados para realizar a ordenação em ordem crescente.

# Estrutura do Repositório  
**/algoritmos**: Contém os arquivos fonte de cada algoritmo de ordenação, sendo que cada algoritmo possui sua própria função main.  
**/input**: Contém os conjuntos de teste utilizados para avaliar os algoritmos de ordenação.    
**/output**: É o diretório onde serão armazenados os arquivos ordenados e um arquivo CSV contendo o nome do arquivo e o tempo de execução em milissegundos.

# Conjuntos de Teste
Cada algoritmo de ordenação será testado com os seguintes conjuntos de teste:   

**Valores Desordenados**: Conjunto de valores gerados aleatoriamente e desordenados.    
**Valores Ordenados em Ordem Crescente**: Conjunto de valores gerados em ordem crescente.   
**Valores Ordenados em Ordem Decrescente**: Conjunto de valores gerados em ordem decrescente.       
**Valores Constantes**: Conjunto de valores contendo apenas um valor constante repetido várias vezes.  
**Valores Parcialmente Ordenados Crescentemente (90%) com Dados Desordenados no Final**: Conjunto de valores em que 90% dos elementos estão ordenados em ordem crescente e 10% estão desordenados no final.     
**Valores Parcialmente Ordenados Crescentemente (90%) com Dados Desordenados no Início**: Conjunto de valores em que 90% dos elementos estão ordenados em ordem crescente e 10% estão desordenados no início.       

Os conjuntos de teste terão tamanhos variados entre 200.000 e 1.000.000 de elementos, com intervalos de 200.000 (200.000, 400.000, ..., 1.000.000).

# Análise Estatística
Será realizada uma análise estatística de cada algoritmo de ordenação, gerando gráficos do tamanho da amostra versus tempo de execução.     
Além disso, serão realizadas análises individuais para cada conjunto de teste, utilizando os respectivos tamanhos de amostra, para cada algoritmo de ordenação.

# Execução
Para executar cada algoritmo de ordenação individualmente, acesse o diretório **/algoritmos** e compile e execute o arquivo correspondente ao algoritmo desejado.   

Os resultados da ordenação serão armazenados no diretório **/output**, onde os arquivos ordenados serão gerados junto com um arquivo CSV contendo o nome do arquivo e o tempo de execução em milissegundos.
