# final_project

Progetto finale per l'academy di Sistemi Informativi.
L'applicazione è composta da un backend realizzato in Spring Boot, con l'ausilio di JAX-RS e Jersey per effettuare delle chiamate REST al sito open-meteo.com. Questo sito restituisce informazioni correnti e orarie sul meteo. Si sono scelte due città italiane, Messina (la mia città) e Palermo.
Queste informazioni vengono salvate nel database solo se il salvataggio è effettivamente richiesto.
Il frontend è realizzato in Angular; viene prodotta una pagina web: nel componente home è presente il componente weather, che si occupa di mostrare le informazioni prese dal backend in una Bootstrap card. 
