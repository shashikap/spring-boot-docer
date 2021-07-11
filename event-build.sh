#/bin/sh

int_setup(){
    echo "-------------------------------------------------------------------------------"
    echo "Init Setup"
    echo "-------------------------------------------------------------------------------"
    docker-compose -f docker-compose-pg.yaml up -d
    echo "-------------------------------------------------------------------------------"
    echo "Init Setup Completed"
    echo "---------------------------------**********------------------------------------"
}

buld_modules(){
    echo "-------------------------------------------------------------------------------"
    echo "Building-Event-Consumer"
    echo "-------------------------------------------------------------------------------"
    mvn -f event-consumer/pom.xml clean install
    echo "-------------------------------------------------------------------------------"
    echo "Event-Consumer-Build-Finished"
    echo "-------------------------------------------------------------------------------"
    echo "Building-Event-Crud"
    echo "-------------------------------------------------------------------------------"
    mvn -f event-crud/pom.xml clean install
    echo "-------------------------------------------------------------------------------"
    echo "Event-Crud-Build-Finished"
    echo "---------------------------------**********------------------------------------"
}

build_docker(){
    echo "-------------------------------------------------------------------------------"
    echo "Building-Event-Consumer-Docker"
    echo "-------------------------------------------------------------------------------"
    docker build -t event-consumer:v1.00  -f event-consumer/Dockerfile .
    echo "-------------------------------------------------------------------------------"
    echo "Event-Consumer-Build-Docker-Finished"
    echo "-------------------------------------------------------------------------------"
    echo "Building-Event-Crud-Docker"
    docker build -t event-crud:v1.00  -f event-crud/Dockerfile .
    echo "-------------------------------------------------------------------------------"
    echo "Event-Crud-Build-Docker-Finished"
    echo "---------------------------------**********------------------------------------"
}

run_setup(){
    docker-compose -f docker-compose.yaml build
    docker-compose -f docker-compose.yaml up
}

for var in "$@"
do
    if [[ "$var" == "init" ]]
    then
        int_setup
    fi

    if [[ "$var" == "maven" ]]
    then
        buld_modules
    fi

    if [[ "$var" == "docker" ]]
    then
        build_docker
    fi

    if [[ "$var" == "run" ]]
    then
        run_setup
    fi
done
