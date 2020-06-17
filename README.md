# Tweets Analyzer

Tweets Analyzer è una Spring Boot Web App sviluppata in Java, in grado di interfacciarsi con le API Search di Twitter per raccogliere dati inerenti ad alcuni hashtag a scelta dell'utente, al fine di sviluppare uno studio di analisi statistica e di impatto sui dati raccolti.

All'avvio del programma, viene eseguita l'autenticazione all'API di Twitter, per raccogliere un massimo di 100 post (fino a 7 giorni prima, caratteristica dell'API Search di base) su hashtag scelti dall'Utente. Nel caso in cui non venisse effettuata alcuna scelta sugli hashtag da analizzare, l'Applicazione procederà alla raccolta automatica dei seguenti:

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

Una volta interrogata l'API, vengono restituiti i dati sotto forma di JSON. Si procede dunque al parsing dei campi di interesse all'interno di una Map creata per l'occasione.

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

## Scelta degli Hashtag

All'Avvio dell'Applicazione viene eseguito in automatico il Download dei Tweet relativi agli hashtag nel File di Testo ***hashtags.txt*** della cartella ***Files***.

Se l'Utente **non** modifica il File, verranno scaricati gli hashtags già presenti nel suddetto.
Il File di Testo deve essere scritto dall'Utente in modo tale che i nomi relativi a ciascun hashtag si trovino incolonnati uno sopra l'altro. Il File **non** deve essere vuoto. Inoltre **non** sono ammessi caratteri come lo spazio, o caratteri speciali (es: ***#***) sulla stessa riga.

Eventuali errori di inserimento da parte dell'Utente sono gestiti in ogni caso da opportune Eccezioni.

## Utilizzo della Spring Web App

La Spring Web App da noi sviluppata permette di utilizzare le seguenti funzioni mediante richieste **API REST (GET o POST)** :


|Tipo di Chiamata|Rotta| Descrizione |
|--|--|--|
|**GET**| **/metadata** |Elenco dei campi di interesse per l'analisi e il tipo di dati contenuti|
|**GET**|**/data**| Elenco dei tweet raccolti|
|**POST**|**/data**| Elenco dei dati sottoposti a Filtri applicati dall'Utente|    
|**GET**|**/stats**| Statistiche su alcuni parametri dei posts|
|**POST**|**/stats**| Statistiche su alcuni parametri di posts, dopo essere stati filtrati|

## Rotte dell'applicazione
Una volta avviata l'app, quest'ultima sarà in ascolto all'indirizzo *localhost:8080*. Le seguenti rotte, con le relative richieste, possono essere inserite in Postman per accedere alle funzionalità dell'app:


> **GET /metadata**

Restituisce i metadata (ovvero i campi d'interesse analizzati) precedentemente specificati.


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
            },
            {
            ....

Restituisce tutti i dati scaricati in seguito all'avvio della app e al login all'interno dell'API.


> **GET /stats**

    [
       {
        "hashtag":  "Prada",
        "total likes":  1089882,
        "total retweets":  15013,
        "percLikes":  "3.8%",
        "percRetweets":  "0.76%",
        "postsPerDay":  48.5
        },
        {
        ...
     ]

Restituisce le statistiche sul numero di Likes e Retweets totalizzati per ogni hashtag, oltre ad una percentuale di likes e retweets sul totale dei post analizzati e al numero di post al giorno.


> **POST /data**

Questa particolare chiamata, a seguito dell'inserimento di determinati parametri passati all'app attraverso un body, restituisce dei dati filtrati.

## Filtri

I filtri realizzati sono i seguenti:

|Chiave del Filtro| Descrizione |
|--|--|
| # | Filtra in base ad uno o più Hashtag |
| Language | Filtra in base ad una o più Lingue |
|Followers| Filtra i Tweet scritti dagli Account con un certo numero, o una fascia di Followers |
|Retweets| Filtra i Tweet che hanno avuto un certo numero, o una fascia di Retweets |
|Likes| Filtra i Tweet che hanno avuto un certo numero, o una fascia di likes |
|After| Filtra i Tweet scritti dopo una determinata Data e Ora |
|Before| Filtra i Tweet scritti prima di una determinata Data e Ora |
|Between| Filtra i Tweet compresi tra due determinate Date e Ore |

## Filtro: #
 
Ad esempio, si può filtrare rispetto ad alcuni hashtags in particolare (es. "Prada" e "Gucci"):

Si genera un body del tipo: 

    {
      "#" : [ "Prada", "Gucci" ]
    }

per avere restituiti soltanto i Tweets relativi agli hashtags Prada e Gucci.

E' anche possibile costruire un Filtro Complesso, composto di più Body, per applicare più filtri di diversa natura, per esempio rispetto ad uno o più hashtags, uniti ad una data o un linguaggio

    [
      {"#" : ["Gucci", "Dior"]}, 
      {"Language" : "en"}, 
      {"Between" : ["07/06/2020", "09/06/2020"]}
    ]

> **POST /stats**

È possibile ottenere Statistiche su un numero limitato di Dati. Infatti specificando la rotta ***/stats*** con chiamata ***POST***, viene data all'Utente la possibilità di inserire un Filtro dei precedenti, per limitare il numero di Dati da Analizzare. In seguito all'inserimento del body saranno restituite delle Statistiche sui Tweet filtrati.    


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


**com.univpm.TweetAnalyzer.model.time**

![Time](UML/TimePackageUML.jpg)


**com.univpm.TweetAnalyzer.exception**

![Exception](UML/ExceptionPackageUML.jpg)


**com.univpm.TweetAnalyzer.service**

![Service](UML/ServicePackageUML.jpg)


**com.univpm.TweetAnalyzer.other.filter**

![Filter](UML/FilterPackageUML.jpg)


**GET /metadata**

![Metadata](UML/getMetadataUML.jpg)


**GET /data**

![Data](UML/getDataUML.jpg)


**POST /data**

![Data](UML/postDataUML.jpg)


**GET /stats**

![Stats](UML/getStatsUML.jpg)


**POST /stats**

![Stats](UML/postStatsUML.jpg)
