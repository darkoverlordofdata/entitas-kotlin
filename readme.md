# Entitas ECS - Kotlin


Kotlin implementation port of https://github.com/sschmid/Entitas-CSharp

core module is a demo
entitas module is the library

### Entitas cli
use entitas cli to generate empty components & extensions

    git clone git@github.com:darkoverlordofdata/entitas-ts.git
    cd entitas-ts
    npm install . -g

    Kotlin Usage:
    entitas init namespace
    entitas create -c name field:type... 
    entitas create -s name interface...
    entitas generate -p kotlin
    
    Options:
    -c  [--component] # create a component
    -s  [--system]    # create a system
    -p  [--platform]  # target platform for generated code



