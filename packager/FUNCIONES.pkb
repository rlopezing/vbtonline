CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.FUNCIONES
IS

FUNCTION FORMATO_VACIO (v_dato VARCHAR2) RETURN VARCHAR2
IS

dato VARCHAR2(1000):= ' ';
BEGIN
 IF (v_dato IS NULL) THEN
     RETURN ' ';
 ELSE
     dato:= TRIM(TO_CHAR(v_dato));
     IF (LENGTH(dato) = 0) THEN
        RETURN ' ';
     ELSE
        RETURN dato;
     END IF;
 END IF;

EXCEPTION
 WHEN OTHERS THEN
  RETURN ' ';
END FORMATO_VACIO;

FUNCTION FORMATO_VACIO_HTML (v_dato VARCHAR2) RETURN VARCHAR2
IS

dato VARCHAR2(1000):= '&' || 'nbsp;';
BEGIN
 IF (v_dato IS NULL) THEN
     RETURN '&' || 'nbsp;';
 ELSE
     dato:= TRIM(TO_CHAR(v_dato));
     IF (LENGTH(dato) = 0) THEN
        RETURN '&' || 'nbsp;';
     ELSE
        RETURN dato;
     END IF;
 END IF;

EXCEPTION
 WHEN OTHERS THEN
  RETURN '&' || 'nbsp;';
END FORMATO_VACIO_HTML;

PROCEDURE Send_Email (sender IN VARCHAR2, recipient IN VARCHAR2,
subject IN VARCHAR2, message IN VARCHAR2) IS
mailhost VARCHAR2(30) := '192.168.223.14';
--mailhost VARCHAR2(30) := '172.30.1.219';
mail_conn UTL_SMTP.Connection;
crlf VARCHAR2(2) := CHR(13) || CHR(10);
mesg VARCHAR2(30000);
BEGIN
   mail_conn := UTL_SMTP.open_connection(mailhost, 25);
   mesg:= 'Date: '||TO_CHAR(SYSDATE,'dd Mon yy hh24:mi:ss')||crlf;
   mesg:= mesg||'From: <'||sender||'>' || crlf ||'Subject: '||subject||crlf;
   mesg:= mesg||'To: '||recipient||crlf||''||crlf||message;
   UTL_SMTP.helo(mail_conn, mailhost);
   UTL_SMTP.mail(mail_conn, sender);
   UTL_SMTP.rcpt(mail_conn, recipient);
   UTL_SMTP.data(mail_conn, mesg);
   UTL_SMTP.quit(mail_conn);
END Send_Email;

PROCEDURE Send_Html_Email (sender IN VARCHAR2, recipient IN VARCHAR2,
subject IN VARCHAR2, message IN VARCHAR2) IS
mailhost VARCHAR2(30) := '192.168.223.14';
--mailhost VARCHAR2(30) := '172.30.1.219';
mail_conn UTL_SMTP.Connection;
crlf VARCHAR2(2) := CHR(13) || CHR(10);
mesg VARCHAR2(32767);
BEGIN
   mail_conn := UTL_SMTP.open_connection(mailhost, 25);
   mesg:= 'MIME-Version: 1.0' ||  chr(13) || chr(10);
   mesg:= 'Content-type: text/html; charset=iso-8859-1' ||crlf;
   mesg:= mesg||'Date: '||TO_CHAR(SYSDATE,'dd Mon yy hh24:mi:ss')||crlf;
   mesg:= mesg||'From: <'||sender||'>' || crlf ||'Subject: '||subject||crlf;
   mesg:= mesg||'To: '||recipient||crlf||''||crlf||message;

   UTL_SMTP.helo(mail_conn, mailhost);
   UTL_SMTP.mail(mail_conn, sender);
   UTL_SMTP.rcpt(mail_conn, recipient);
   
   UTL_SMTP.open_data(mail_conn);
   UTL_SMTP.write_raw_data(mail_conn, utl_raw.cast_to_raw(mesg));

   UTL_SMTP.close_data(mail_conn);
   UTL_SMTP.quit(mail_conn);
END Send_Html_Email;


PROCEDURE Send_Email_Destinatarios (sender IN VARCHAR2, recipient IN VARCHAR2,
subject IN VARCHAR2, message IN VARCHAR2) IS
mailhost VARCHAR2(30) := '192.168.223.14';
--mailhost VARCHAR2(30) := '172.30.1.219';
mail_conn UTL_SMTP.Connection;
crlf VARCHAR2(2) := CHR(13) || CHR(10);
mesg VARCHAR2(30000);
   I            NUMBER (5) := 1;
   J            NUMBER (5) := 1;
   CC           VARCHAR2 (100);
   RECIPIENT_AUX VARCHAR2 (3000);
BEGIN

  RECIPIENT_AUX:= RECIPIENT;
   
    IF SUBSTR (RECIPIENT, LENGTH(recipient)) != ',' THEN
    
        RECIPIENT_AUX:= RECIPIENT || ',';
      
    END IF;
    
   mail_conn := UTL_SMTP.open_connection(mailhost, 25);
   mesg:= 'Date: '||TO_CHAR(SYSDATE,'dd Mon yy hh24:mi:ss')||crlf;
   mesg:= mesg||'From: <'||sender||'>' || crlf ||'Subject: '||subject||crlf;
   mesg:= mesg||'To: '||RECIPIENT_AUX||crlf||''||crlf||message;
   UTL_SMTP.helo(mail_conn, mailhost);
   UTL_SMTP.mail(mail_conn, sender);
   --UTL_SMTP.rcpt(mail_conn, recipient);
   
    IF SUBSTR (RECIPIENT, LENGTH(recipient)) != ',' THEN
    
        RECIPIENT_AUX:= RECIPIENT || ',';
      
    END IF;
    
   WHILE INSTR (RECIPIENT_AUX, ',', I) != 0
   LOOP
      CC := SUBSTR (RECIPIENT_AUX, I, INSTR (RECIPIENT_AUX, ',', I) - J);
      I := INSTR (RECIPIENT_AUX, ',', I) + 1;
      J := I;
      UTL_SMTP.RCPT (MAIL_CONN, CC);
   END LOOP;
   
   UTL_SMTP.data(mail_conn, mesg);
   UTL_SMTP.quit(mail_conn);
END Send_Email_Destinatarios;


PROCEDURE Send_Email_Destinatarios_p (sender IN VARCHAR2, recipient IN VARCHAR2,
subject IN VARCHAR2, message IN VARCHAR2) IS
mailhost VARCHAR2(30) := '192.168.223.14';
--mailhost VARCHAR2(30) := '172.30.1.219';
mail_conn UTL_SMTP.Connection;
crlf VARCHAR2(2) := CHR(13) || CHR(10);
mesg VARCHAR2(30000);
   I            NUMBER (5) := 1;
   J            NUMBER (5) := 1;
   CC           VARCHAR2 (100);
   RECIPIENT_AUX VARCHAR2 (500);
BEGIN

    RECIPIENT_AUX:= RECIPIENT;
    
    IF SUBSTR (RECIPIENT, LENGTH(recipient)) != ',' THEN
    
        RECIPIENT_AUX:= RECIPIENT || ',';

    END IF;
    
    
   mail_conn := UTL_SMTP.open_connection(mailhost, 25);
   mesg:= 'Date: '||TO_CHAR(SYSDATE,'dd Mon yy hh24:mi:ss')||crlf;
   mesg:= mesg||'From: <'||sender||'>' || crlf ||'Subject: '||subject||crlf;
   mesg:= mesg||'To: '||RECIPIENT_AUX||crlf||''||crlf||message;
   UTL_SMTP.helo(mail_conn, mailhost);
   UTL_SMTP.mail(mail_conn, sender);
   --UTL_SMTP.rcpt(mail_conn, recipient);


    
   WHILE INSTR (RECIPIENT_AUX, ',', I) != 0
   LOOP
      CC := SUBSTR (RECIPIENT_AUX, I, INSTR (RECIPIENT_AUX, ',', I) - J);
      I := INSTR (RECIPIENT_AUX, ',', I) + 1;
      J := I;
      

      UTL_SMTP.RCPT (MAIL_CONN, CC);
   END LOOP;
   
   UTL_SMTP.data(mail_conn, mesg);
   UTL_SMTP.quit(mail_conn);
END Send_Email_Destinatarios_p;


PROCEDURE Send_Html_Email_destinatarios (sender IN VARCHAR2, recipient IN VARCHAR2,
subject IN VARCHAR2, message IN VARCHAR2) IS
--mailhost VARCHAR2(30) := '192.168.223.13';
mailhost VARCHAR2(30) := 'mail.vbtbank.com';
--mailhost VARCHAR2(30) := '172.30.1.219';
mail_conn UTL_SMTP.Connection;
crlf VARCHAR2(2) := CHR(13) || CHR(10);
mesg VARCHAR2(32767);


------

   I            NUMBER (5) := 1;
   J            NUMBER (5) := 1;
   CC           VARCHAR2 (100);
   RECIPIENT_AUX VARCHAR2 (500);
BEGIN


   RECIPIENT_AUX:= RECIPIENT;
   
    IF SUBSTR (RECIPIENT, LENGTH(recipient)) != ',' THEN
    
        RECIPIENT_AUX:= RECIPIENT || ',';
      
    END IF;
    
    
    
   mail_conn := UTL_SMTP.open_connection(mailhost, 25);
   mesg:= 'MIME-Version: 1.0' ||  chr(13) || chr(10);
   mesg:= 'Content-type: text/html; charset=iso-8859-1' ||crlf;
   mesg:= mesg||'Date: '||TO_CHAR(SYSDATE,'dd Mon yy hh24:mi:ss')||crlf;
   mesg:= mesg||'From: <'||sender||'>' || crlf ||'Subject: '||subject||crlf;
   --mesg:= mesg||'To: '||recipient||crlf||''||crlf||message;
   mesg:= mesg||'To: '||RECIPIENT_AUX||crlf||''||crlf||message;

   UTL_SMTP.helo(mail_conn, mailhost);
   UTL_SMTP.mail(mail_conn, sender);
   --UTL_SMTP.rcpt(mail_conn, recipient);
      WHILE INSTR (RECIPIENT_AUX, ',', I) != 0
   LOOP
      CC := SUBSTR (RECIPIENT_AUX, I, INSTR (RECIPIENT_AUX, ',', I) - J);
      I := INSTR (RECIPIENT_AUX, ',', I) + 1;
      J := I;
      UTL_SMTP.RCPT (MAIL_CONN, CC);
   END LOOP;
   
   UTL_SMTP.open_data(mail_conn);
   UTL_SMTP.write_raw_data(mail_conn, utl_raw.cast_to_raw(mesg));

   UTL_SMTP.close_data(mail_conn);
   UTL_SMTP.quit(mail_conn);
END Send_Html_Email_destinatarios;


END FUNCIONES;
/

