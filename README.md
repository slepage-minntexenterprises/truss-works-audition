# Truss Works Audition Project

### Installing

```
git clone https://github.com/slepage-minntexenterprises/truss-works-audition.git
```

```
mvn clean install
```

### Preparing to run 
```
cd target/
```

### Running Application

```
java -classpath audition-0.0.1-SNAPSHOT-jar-with-dependencies.jar  com.truss.audition.CsvNormalizationApp ../sample.csv ./normalized.csv
```