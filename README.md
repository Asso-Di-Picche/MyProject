# Tweets Analyzer

Tweets Analyzer è una Spring Boot Web App sviluppata in Java, in grado di interfacciarsi con le API Search di Twitter per raccogliere dati inerenti ad alcuni hashtag a scelta dell'utente, al fine di sviluppare uno studio di analisi statistica e di impatto sui dati raccolti.

All'avvio del programma, viene eseguita l'autenticazione all'API di Twitter, per raccogliere un massimo di 100 post (fino a 7 giorni prima, caratteristica dell'API Search di base) su 10 hashtag diversi da noi scelti:

 - **Prada**  
 - **Armani**   
 - **Ferragamo**   
 - **Gucci**   
 - **Versace**  
 - **Trussardi**  
 - **Valentino**   
 - **Zegna**
 - **Dior**
 - **Benetton**

Una volta interrogata l'API, vengono restituiti i dati sotto forma di file JSON. Si procede dunque al parsing dei campi di interesse all'interno di una Map creata per l'occasione.

I campi di interesse scelti per l'analisi sono i seguenti: 

 - **Hashtag** 
- **Data** 
- **PostID**
- **Username** 
- **Language** 
- **Followers**
- **Retweets** 
- **Likes** 

Per il test della Web App viene utilizzato **POSTMAN**, un tool utile per il testing delle API.

## Utilizzo della Spring Web App

La Spring Web App da noi sviluppata permette di utilizzare le seguenti funzioni mediante richieste **API REST (GET o POST)** :


|Tipo di funzione| Descrizione |
|--|--|
| **Metadata** |elenco dei campi di interesse per l'analisi e il tipo di dati contenuti|
|**Data**| elenco fino ad un massimo di 100 post per ogni hashtag    
|**Stats**| Statistiche su alcuni parametri dei posts
|**Filters**| Filtraggio dei dati rispetto ad alcuni parametri


## Rotte dell'applicazione
Una volta avviata l'app, quest'ultima sarà in ascolto all'indirizzo *localhost:8080*. Le seguenti rotte, con le relative richieste, possono essere inserite in Postman per accedere alle funzionalità dell'app:


> **GET /data**

    "Prada":  {
         "0":  {
                "postID": 1271368152404725762,
                "language":  "zh",
                "date":  "Fri Jun 12 09:06:03 +0000 2020",
                "username":  "pincoppalino",
                "followers":  39,
                "likes":  2,
                "retweets":  0
            }
            ....

Restituisce tutti i dati scaricati in seguito all'avvio della app e al login all'interno dell'API.


> **GET /metadata**

Restituisce i metadata (ovvero i campi d'interesse analizzati) precedentemente specificati.


> **GET /stats**

    {
        "hashtag":  "Prada",
        "total likes":  1089882,
        "total retweets":  15013,
        "percLikes":  "3.8%",
        "percRetweets":  "0.76%",
        "postsPerDay":  48.5
     }
     ...

Restituisce le statistiche sul numero di likes e retweets totalizzato da un determinato hashtag su un massimo di 100 post, oltre ad una percentuale di likes e retweets sul totale dei post analizzati e al numero di post al giorno.


> **POST /data**

Questo particolare metodo, a seguito dell'inserimento di determinati parametri passati all'app attraverso un body, restituisce dei dati filtrati.

I filtri realizzati sono i seguenti:

|Tipo di filtro| Esempio di body da inserire |
|--|--|
| Hashtag | **{ "#" : "Prada" }** - oppure - **{ "#" : [ "Gucci", "Dior" , "Prada" ] }**|
| Lingua    | **{ "Language" : "it" }** - oppure - **{ "Language" : [ "it", "ja", "fr" ] }**
|Followers, retweets e likes| **{ "Followers" : [ "<" , "345"] }**
|Data| **{ "Before" : "07/06/2020" }** - oppure - **{ "After" : "07/06/2020" }** - oppure - **{ "Between" : [ "07/06/2020" , "09/06/2020" ] }**
 
Ad esempio, si può filtrare rispetto ad alcuni hashtags in particolare (es. "Prada" e "Gucci"):

Si genera un body del tipo: 

    {
      "#" : [ "Prada", "Gucci" ]
    }

per avere come risultato, sempre all'interno di un file JSON, fino ad un massimo di 100 posts (per hashtag) relativi soltanto agli hashtags Prada e Gucci.

E' anche possibile costruire un body per applicare più filtri di diversa natura, per esempio rispetto ad uno o più hashtags, uniti ad una data o un linguaggio

    {
      ["#" : ["Gucci", "Dior"]], 
      ["Language" : ["it", "ja", "fr"]], 
      ["Between" : ["07/06/2020", "09/06/2020"]]
    }



## Gestione delle eccezioni

Sono state sviluppate anche delle eccezioni personalizzate, che vengono lanciate a seconda dei diversi errori generati:

 
|Eccezione| Descrizione |
|--|--|
| **DuplicateFilterException** |Se il filtro è composto da più filtri contenenti la stessa chiave |
|**IllegalFilterValueException** | Se uno dei valori inseriti dall'utente nel filtro è errato
|**IllegalTimeException**| Se un'eventuale data del filtro è stata inserita in un modo **NON** consentito
|**IllegalFilterValueSizeException**| Se uno dei valori inseriti dall'utente nel filtro è di dimensione **NON** consentita
|**IllegalFilterKeyException**| Se una delle chiavi inserite dall'utente nel filtro è errata

## UML

**PACKAGE**

![Package](UML/Package.jpg)


**USE CASE DIAGRAM**

![UseCase](UML/UseCaseDiagram.jpg)


**com.univpm.TweetAnalyzer.bin**

![Bin](UML/BinPackageUML.jpg)


**com.univpm.TweetAnalyzer.controller**

![Controller](UML/ControllerPackageUML.jpg)


**com.univpm.TweetAnalyzer.model**

![Model](UML/ModelPackageUML.jpg)


**com.univpm.TweetAnalyzer.time**

![Time](UML/TimePackageUML.jpg)


**com.univpm.TweetAnalyzer.exception**

![Exception](UML/ExceptionPackageUML.jpg)


**com.univpm.TweetAnalyzer.service**

![Service](UML/ServicePackageUML.jpg)


**com.univpm.TweetAnalyzer.filter**

![Filter](UML/FilterPackageUML.jpg)


**GET /data**

![Data](UML/getDataUML.jpg)


**GET /metadata**

![Metadata](UML/getMetadataUML.jpg)











