parser grammar HuaweiParser;

import HuaweiVLAN;

options {
    superClass='org.batfish.grammar.BatfishParser';
    tokenVocab=HuaweiLexer;
}


s_sysname
:
    SYSNAME (name=NAME)+ NEWLINE
;

s_vlan
:
    VLAN
     (  singleton=NUMBER
        | BATCH start=NUMBER+ (TO end=NUMBER+)?
     )+ NEWLINE
;

statement
:
    s_sysname
    | s_vlan
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
