create table organizations
(
	oid serial not null,
	organization varchar(50) not null,
	email varchar(20) not null,
	password varchar(20) not null,
	enabled bool default true not null,
	authority varchar(20) default 'ROLE_USER' not null
);

create unique index organizations_oid_uindex
	on organizations (oid);

create unique index organizations_organization_uindex
	on organizations (organization);

alter table organizations
	add constraint organizations_pk
		primary key (oid);

create table settings
(
	oid int not null
		constraint settings_organizations_oid_fk
			references organizations,
	deferred_sms int default 0 not null,
	quantity_sms int default 0 not null,
	interval int default 0 not null
);

create table sms_templates
(
	oid int not null
		constraint sms_templates_organizations_oid_fk
			references organizations,
	tid serial not null,
	template varchar(255) not null
);

create unique index sms_templates_tid_uindex
	on sms_templates (tid);

create table keywords
(
	oid int not null
		constraint keywords_organizations_oid_fk
			references organizations,
	kid serial not null,
	keyword varchar(25) not null
);

create unique index keywords_kid_uindex
	on keywords (kid);

create table endline_template
(
	oid int not null
		constraint endline_template_organizations_oid_fk
			references organizations,
	etid serial not null,
	endline_template varchar(50) default '.' not null
);

create unique index endline_template_etid_uindex
	on endline_template (etid);

create table contacts
(
	oid int not null
		constraint contacts_organizations_oid_fk
			references organizations,
	cid serial not null,
	name varchar(20) not null,
	second_name varchar(20),
	third_name varchar(20),
	phone_number varchar(50) not null
);

create unique index contacts_info_cid_uindex
	on contacts (cid);
	
create table calls_info
(
        oid int not null
                constraint calls_info_organizations_oid_fk
                        references organizations,
        convid serial not null,
        conversation_date date not null,
        phone_number varchar(50) not null,
        parsed_sms varchar(5000) not null
);

create unique index calls_info_conv_id_uindex
        on calls_info (convid);

create table auth_token
(
    oid int not null constraint contacts_organizations_oid_fk references organizations,
	tokenId serial not null,
	token varchar(32)
    );



