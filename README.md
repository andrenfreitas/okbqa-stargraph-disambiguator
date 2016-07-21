# okbqa-stargraph-disambiguator

Disambiguation Module based on STARGRAPH semantic database
====

Manual
======
- Run the following to start (best in a screen)
mvn clean exec:java

Infos on running at https://sites.google.com/site/okbqa3/development/deployments
- You can use REST and POST
- REST at http://IP:2357/stargraph/run?data = "…"
- Example REST Call
http://localhost:2357/stargraph/run?data={"question":"Who did that and wo?","slots" : [ {"s" : "?x", "p" : "verbalization", "o" : "shopping center"}, {"s" : "?x", "p" : "is", "o" : "rdf:Class_rdf:Resource"}, {"s" : "?y", "p" : "verbalization", "o" : "University of Kiel"} ] }
- POST at http://IP:2357/stargraph/disambiguate
- Example POST Call (test.json in test_data folder)
curl -i -H "Content-Type: application/json" -X POST -d @test.json http://IP:2357/stargraph/disambiguate