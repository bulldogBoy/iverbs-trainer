# iverbs-trainer
Console app for learning English irregular verbs in Russian in a quiz format
You can change the language to your own by replacing the value in the "translation" column in sqlite.db
# usage:
```
git clone https://github.com/bulldogBoy/iverbs-trainer.git
cd iverbs-trainer
mvn package
cp sqlite.db target/
cd target
java -jar iverbs-trainer-0.0.1-SNAPSHOT.jar
```
