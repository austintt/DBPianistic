
-- Table: building
CREATE TABLE building ( 
    byui_building_id INTEGER        PRIMARY KEY AUTOINCREMENT,
    building_name    VARCHAR( 45 )  NOT NULL
                                    UNIQUE ON CONFLICT FAIL 
);

INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (2, 'Austin');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (1, 'BYU Idaho Center');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (3, 'Barnes Hall');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (4, 'Benson');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (5, 'Biddulph');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (6, 'Chapman Hall');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (7, 'Hart');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (8, 'Hinckley');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (10, 'Kerr Hall');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (11, 'Kimball');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (12, 'Kirkham Bldg');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (13, 'Lamprecht Hall');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (14, 'Perkins Hall');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (15, 'Ricks');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (16, 'Ricks Hall');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (17, 'Romney');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (18, 'Smith');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (19, 'Snow');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (20, 'Stadium Studio');
INSERT INTO [building] ([byui_building_id], [building_name]) VALUES (21, 'Taylor');

-- Table: room_type
CREATE TABLE room_type ( 
    room_type_id INTEGER        PRIMARY KEY AUTOINCREMENT,
    room_type    VARCHAR( 45 )  NOT NULL
                                UNIQUE ON CONFLICT FAIL 
);

INSERT INTO [room_type] ([room_type_id], [room_type]) VALUES (3, 'Class Room');
INSERT INTO [room_type] ([room_type_id], [room_type]) VALUES (1, 'Concert Room');
INSERT INTO [room_type] ([room_type_id], [room_type]) VALUES (2, 'Practice Room');

-- Table: piano_make
CREATE TABLE piano_make ( 
    make_id   INTEGER        PRIMARY KEY AUTOINCREMENT,
    make_name VARCHAR( 45 )  NOT NULL
                             UNIQUE ON CONFLICT IGNORE 
);

INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (2, 'Baldwin');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (8, 'Baldwin P&O');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (14, 'Charles Walter');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (11, 'Hamburg D');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (1, 'Kawai');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (12, 'Kawai American');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (13, 'Knabe');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (7, 'Mason & Hamilton');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (10, 'New Yamaha');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (6, 'Petrof');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (4, 'Roland');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (5, 'Steinway');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (9, 'Steinway M');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (3, 'Yamaha');
INSERT INTO [piano_make] ([make_id], [make_name]) VALUES (15, 'Yamaha Digital');

-- Table: piano_model
CREATE TABLE piano_model ( 
    model_id          INTEGER         PRIMARY KEY AUTOINCREMENT,
    model_name        VARCHAR( 45 )   NOT NULL
                                      UNIQUE ON CONFLICT IGNORE,
    model_description VARCHAR( 255 ) 
);

INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (1, 'UST7', 'Professional upright piano designed for schools and other institutions');
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (2, 'KR-370', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (3, 'W190', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (4, 'P22LO', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (5, 'KR377A', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (6, 'UST-9', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (7, 'KG2C', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (8, 'P22', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (9, 'L', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (10, 'S2412', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (11, 'KG2A', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (12, 'DU1A', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (13, 'KG-2D', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (14, 'R', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (15, 'KG2D', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (16, 'PU1A', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (17, 'MX 22', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (18, 'D+B', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (19, 'T927', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (20, 'KR577', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (21, 'P22L0', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (23, 118, null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (24, 'RX2', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (26, 'D', null);
INSERT INTO [piano_model] ([model_id], [model_name], [model_description]) VALUES (27, 'CF11', null);

-- Table: piano_type
CREATE TABLE piano_type ( 
    type_id   INTEGER        PRIMARY KEY AUTOINCREMENT,
    type_text VARCHAR( 45 )  NOT NULL 
);

INSERT INTO [piano_type] ([type_id], [type_text]) VALUES (1, 'Upright');
INSERT INTO [piano_type] ([type_id], [type_text]) VALUES (2, 'Grand');
INSERT INTO [piano_type] ([type_id], [type_text]) VALUES (3, 'Spinet');
INSERT INTO [piano_type] ([type_id], [type_text]) VALUES (4, 'Console');
INSERT INTO [piano_type] ([type_id], [type_text]) VALUES (5, 'Studio');

-- Table: condition
CREATE TABLE condition ( 
    condition_id   INTEGER        PRIMARY KEY AUTOINCREMENT,
    condition_text VARCHAR( 45 )  NOT NULL 
);

INSERT INTO [condition] ([condition_id], [condition_text]) VALUES (1, 'Excellent');
INSERT INTO [condition] ([condition_id], [condition_text]) VALUES (2, 'Good');
INSERT INTO [condition] ([condition_id], [condition_text]) VALUES (3, 'Fair');
INSERT INTO [condition] ([condition_id], [condition_text]) VALUES (4, 'Poor');

-- Table: piano
CREATE TABLE piano ( 
    piano_sk      INTEGER        PRIMARY KEY AUTOINCREMENT,
    byui_piano_id INTEGER        NOT NULL,
    make_id       INTEGER        REFERENCES piano_make ( make_id ),
    model_id      INTEGER        REFERENCES model ( model_id ),
    type_id       INTEGER        REFERENCES piano_type ( type_id ),
    mfg_serial    VARCHAR( 45 )  UNIQUE,
    year          DATETIME,
    age           INT,
    building_id   INTEGER        REFERENCES building ( byui_building_id ),
    room_number   INTEGER,
    room_type_id  INTEGER        REFERENCES room_type ( room_type_id ),
    condition_id  INTEGER        REFERENCES condition ( condition_id ),
    cost          FLOAT 
);


-- Table: piano_service_history
CREATE TABLE piano_service_history ( 
    job_id_pk                    INTEGER         PRIMARY KEY AUTOINCREMENT
                                                 NOT NULL,
    byui_piano_id                INTEGER         NOT NULL
                                                 REFERENCES piano ( byui_piano_id ),
    date_of_service              DATETIME        NOT NULL,
    action_performed             VARCHAR( 255 )  NOT NULL,
    service_notes                VARCHAR( 255 ),
    next_service_date            DATE,
    action_performed_by          VARCHAR( 255 )  NOT NULL,
    future_service_notes         VARCHAR( 255 ),
    previous_building_if_moved   INTEGER         REFERENCES building ( byui_building_id ),
    previous_room_if_moved       INTEGER,
    current_relative_humidity    INTEGER,
    current_relative_temperature INTEGER 
);

