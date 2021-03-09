# song-list-application

## Tech stack
- Java 11
- JUnit 5
- Maven 3.6
- Mockito 3
- Assertj 3

## Prerequisites
JDK must be installed. There is no need to install build tool, maven wrapper is already contained in project. 

## Usage
Clone this repo and run 
#### Windows
```bat
compile.bat && run.bat [arguments]
```

#### Linux
```bash
./compile.sh && ./run.sh [arguments]
```

Arguments are path or paths to files with songs. Required format: .xml, .csv, .json.

### Format of input file

#### CSV
```csv
Title,Author,Album,Category,Votes
Space Oddity,David Bowie,David Bowie,Rock,7
```

#### XML
```xml
<songs>
    <song>
        <title>Space Oddity</title>
        <author>David Bowie</author>
        <album>David Bowie</album>
        <category>Rock</category>
        <votes>7</votes>
    </song>
</songs>
```

#### JSON
```json
[
  {
    "title": "Space Oddity",
    "author": "David Bowie",
    "album": "David Bowie",
    "category": "Rock",
    "votes": 7
  }
]
```

## Features
- add new song
- vote for chosen song
- reset votes for chosen song or for all
- create report for top3, top10, all or sorted by category

## Inspiration

App created for recruitment purpose.
