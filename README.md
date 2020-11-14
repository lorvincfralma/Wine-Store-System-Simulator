# Wine-Store-System-Simulator
Java-Project

L’obiettivo è definire i diagrammi UML dei casi di uso e delle classi, che documentano un sistema
software per la vendita online di bottiglie di vino, e implementare il sistema in Java, utilizzando in
modo appropriato le tecniche di riferimento della programmazione orientata agli oggetti.
I vini, sono identificati da: nome, produttore, anno, note tecniche, e i vitigni da cui derivano. Si noti
che il sistema deve anche tenere traccia del numero di bottiglie di ogni vino per ogni annata prodotta.
Il sistema interagisce con utenti (persone che vogliono acquistare del vino) e impiegati (persone che
gestiscono la vendita). Ogni persona è identificata da nome, cognome e indirizzo email.
Un utente si può registrare, fare ricerca dei vini per nome e anno di produzione e acquistare bottiglie
di vino dopo un accesso autenticato. Un dipendente può spedire le bottiglie di vino ai clienti e
rimpiazzare le bottiglie di vino vendute.
Ogni volta che un utente fa un ordine per un certo numero di bottiglie di un dato vino, l’operazione
deve essere memorizzata dal sistema che inoltre provvede a decrementare il numero di bottiglie che
possono essere messe in vendita. Se la giacenza diventa nulla, il sistema deve segnalare l’esaurimento
delle scorte e l’impiegato deve quindi acquisire un numero adeguato di bottiglie di quel tipo di vino
e, all’arrivo delle bottiglie, aggiornare i dati sul sistema.
Nel caso in cui le bottiglie di un particolare vino non sono disponibili, allora un utente può
sottoscrivere una richiesta di notifica sulla disponibilità del numero di bottiglie che vorrebbe
acquistare: quando le bottiglie richieste saranno disponibili, il sistema notificherà la loro disponibilità
all’utente.
Definito il codice del sistema, bisognerà implementare una semplice simulazione che:
1) il sistema viene inizializzato con alcuni utenti un impiegato e dei vini;
2) un utente UX si registra e fa l’acquisto di alcune bottiglie di un certo vino UX;
2) un utente UY si registra e fa l’acquisto di tutte le bottiglie di un certo vino UY;
3) un utente UZ si registra e vuole fare l’acquisto di alcune bottiglie del vino UY non più disponibile
e chiede di ricevere una notifica quando il vino UY sarà di nuovo disponibile;
4) l’impiegato aggiunge un certo numero di bottiglie del vino UY e il sistema notifica l’utente UZ
della nuova disponibilità del vino.
Ovviamente, l’avanzamento corretto della simulazione dovranno essere descritte tramite semplici
operazione di scrittura su console.
Il codice dovrà essere formattato con un unico stile di codifica e commentato usando javadoc. I
commenti e gli elementi del codice dovranno essere preferibilmente scritti in lingua inglese.
I Il sistema dovrà essere consegnato tramite un sito di condivisione (e.g., Dropbox e OneDrive). La
consegna dovrà comprendere: un file contenente il nome degli autori dell’assegnamento, le
immagini dei diagrammi realizzati e il codice sorgente.
Il docente alla sua ricezione invierà una risposta di ricezione. Il sistema dovrà essere consegnato non
più tardi di una settimana dall’ultimo laboratorio riguardante l’assegnamento (i.e., in base alla
difficoltà, un assegnamento potrà coinvolgere più di una lezione in laboratorio).
