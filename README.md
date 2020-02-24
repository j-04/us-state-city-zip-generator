# State City Zip generator
## About:
#### The program uses side json file with states, cities and zip codes in compressed form.
#### It transforms the json file in another json with list of objects for convinient use in other software.
#### Also program generating a list with different numbers of cities and their information.
    
## P.S.
#### In the transformed json file you might not to find some cities or zip codes.

## JSON file with list of objects:
> https://github.com/j-04/us-state-city-zip-generator/blob/master/states-cities-zip-codes.json
    
## Data object:
> Java

    class Data {
        String state = null;
        String abbreviation = null;
        String city = null;
        List<Integer> zip = null;
    }

> Kotlin:

    class Data(var state: String? = null, var abbreviation: String? = null, var city: String? = null, var zip: List<Int>? = null)

### TypeToken for Gson:
    TypeToken<List<Data>>() {}

### Technologies:
> - Kotlin 1.3.61
> - Gson 2.8.6
> - Maven