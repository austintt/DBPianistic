-- Table: piano_condition
CREATE TABLE piano_condition ( 
    condition_id   INTEGER      PRIMARY KEY AUTOINCREMENT,
    condition_text VARCHAR(45)  NOT NULL UNIQUE ON CONFLICT IGNORE 
);

-- Table: room_type
CREATE TABLE room_type ( 
    room_type_id   INTEGER        PRIMARY KEY AUTOINCREMENT,
    room_type_text VARCHAR(45)    NOT NULL UNIQUE ON CONFLICT IGNORE 
);

-- Table: piano_type
CREATE TABLE piano_type ( 
    type_id   INTEGER        PRIMARY KEY AUTOINCREMENT,
    type_text VARCHAR(45)    NOT NULL UNIQUE ON CONFLICT IGNORE 
);

-- Table: piano_make
CREATE TABLE piano_make ( 
    make_id   INTEGER        PRIMARY KEY AUTOINCREMENT,
    make_name VARCHAR(45)    NOT NULL UNIQUE ON CONFLICT IGNORE 
);

-- Table: building
CREATE TABLE building ( 
    byui_building_id INTEGER      PRIMARY KEY AUTOINCREMENT,
    building_name    VARCHAR(45)  NOT NULL UNIQUE ON CONFLICT IGNORE 
);

-- Table: piano
CREATE TABLE piano ( 
    piano_sk      INTEGER        PRIMARY KEY AUTOINCREMENT,
    byui_piano_id INTEGER        NOT NULL
                                 UNIQUE,
    make_id       INTEGER        REFERENCES piano_make ( make_id ),
    model_id      INTEGER        REFERENCES model ( model_id ),
    type_id       INTEGER        REFERENCES piano_type ( type_id ),
    mfg_serial    VARCHAR(45)    UNIQUE,
    year          DATETIME,
    building_id   INTEGER        REFERENCES building ( byui_building_id ),
    room_number   INTEGER,
    room_type_id  INTEGER        REFERENCES room_type ( room_type_id ),
    condition_id  INTEGER        REFERENCES piano_condition ( condition_id ),
    cost          FLOAT 
);


-- Table: piano_service_history
CREATE TABLE piano_service_history ( 
    job_id_pk                    INTEGER           PRIMARY KEY AUTOINCREMENT
                                                   NOT NULL,
    byui_piano_id                INTEGER           NOT NULL
                                                   REFERENCES piano ( byui_piano_id ),
    date_of_service              DATETIME          NOT NULL,
    action_performed             VARCHAR(255)      NOT NULL,
    service_notes                VARCHAR(255),
    next_service_date            DATE,
    action_performed_by          VARCHAR(255)      NOT NULL,
    future_service_notes         VARCHAR(255),
    previous_building_if_moved   INTEGER           REFERENCES building ( byui_building_id ),
    previous_room_if_moved       INTEGER,
    current_relative_humidity    INTEGER,
    current_relative_temperature INTEGER 
);

-- Table: piano_model
CREATE TABLE piano_model ( 
    model_id          INTEGER          PRIMARY KEY AUTOINCREMENT,
    model_name        VARCHAR(45)      NOT NULL UNIQUE ON CONFLICT IGNORE,
    model_description VARCHAR(255) 
);
