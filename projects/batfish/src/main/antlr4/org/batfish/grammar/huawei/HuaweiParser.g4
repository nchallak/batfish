parser grammar HuaweiParser;

import HuaweiVLAN;

options {
    superClass='org.batfish.grammar.BatfishParser';
    tokenVocab=HuaweiLexer;
}

s_interface_address_decl
:
    IP ADDRESS iface_addr=IP_ADDRESS mask=IP_ADDRESS NEWLINE
;

s_interface
:
    INTERFACE (name=NAME)+
    NEWLINE
    (
        s_interface_address_decl
    )*
;

s_sysname
:
    SYSNAME (name=NAME)+ NEWLINE
;

s_vlan
:
    VLAN
     (  singleton=NUMBER
        | BATCH start=NUMBER+ (TO end=NUMBER+)?
     )+
     NEWLINE
;

statement
:
    s_sysname
    | s_vlan
    | s_interface
;

huawei_configuration
:
   NEWLINE?
   (
      statement
   )+
   NEWLINE?
   EOF
;
