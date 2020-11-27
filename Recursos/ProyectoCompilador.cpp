GRAMATICA FORMAL LIBRE DE CONTEXTO
GRAMATICA FORMAL LIBRE DE CONTEXTO
GRAMATICA FORMAL LIBRE DE CONTEXTO

<programa>				->	begin <declaraciones><ordenes> end
<declaraciones>			->	<declaracion>;<sig_declaraciones>
<sig_declaraciones>		->	<declaracion>;<sig_declaraciones>
<sig_declaraciones>		->	λ
<declaracion>			->	<tipo><lista_variables>
<tipo>					->	entero
<tipo>					->	real
<lista_variables>		->	identificador <sig_lista_variables>
<sig_lista_variables>	->	,<lista_variables>
<sig_lista_variables>	->	λ
<ordenes>				->	<orden>;<sig_ordenes>
<sig_ordenes>			->	<orden>;<sig_ordenes>
<sig_ordenes>			->	λ
<orden>					->	<condicion>
<orden>					->	<bucle_while>
<orden>					->	<asignar>
<condicion>				->	if(<comparacion>)<ordenes><sig_condicion>
<sig_condicion>			->	end
<sig_condicion>			->	else <ordenes> end
<comparacion>			->	<operador><condicion_op><operador>
<condicion_op>			->	=
<condicion_op>			->	<=
<condicion_op>			->	>=
<condicion_op>			->	<>
<condicion_op>			->	<
<condicion_op>			->	>
<operador>				->	identificador
<operador>				->	<numeros>
<numeros>				->	num_entero
<numeros>				->	num_real
<bucle_while>			->	while(<comparacion>)<ordenes>endwhile
<asignar>				->	identificador := <expresion_arit>
<expresion_arit>		->	(<expresion_arit><operador_arit><expresion_arit>)<exp_arit>
<expresion_arit>		->	identificador <exp_arit>
<expresion_arit>		->	<numeros><exp_arit>
<exp_arit>				->	<operador_arit><expresion_arit><exp_arit>
<exp_arit>				->	λ
<operador_arit>			->	+
<operador_arit>			->	*
<operador_arit>			->	-
<operador_arit>			->	/

/**DESDE AQUI GLC LL(1)*/
<programa>				->	begin <declaraciones><ordenes> end
<declaraciones>			->	<declaracion>;<sig_declaraciones>
<sig_declaraciones>		->	<declaracion>;<sig_declaraciones>| λ
<declaracion>			->	<tipo><lista_variables>
<tipo>					->	entero|real
<lista_variables>		->	identificador <sig_lista_variables>
<sig_lista_variables>	->	,<lista_variables>| λ
<ordenes>				->	<orden>;<sig_ordenes>
<sig_ordenes>			->	<orden>;<sig_ordenes>| λ
<orden>					->	<condicion>|<bucle_while>|<asignar>
<condicion>				->	if(<comparacion>)<ordenes><sig_condicion>
<sig_condicion>			->	end|else <ordenes> end
<comparacion>			->	<operador><condicion_op><operador>
<condicion_op>			->	=|<=|>=|<>|<|>
<operador>				->	identificador|<numeros>
<numeros>				->	num_entero|num_real
<bucle_while>			->	while(<comparacion>)<ordenes>endwhile
<asignar>				->	identificador := <expresion_arit>
<expresion_arit>		->	(<expresion_arit><operador_arit><expresion_arit>)<exp_arit>|identificador <exp_arit>|<numeros><exp_arit>
<exp_arit>				->	<operador_arit><expresion_arit><exp_arit>| λ
<operador_arit>			->	+|*|-|/






/** TERMINALES PARA NUESTRO PROPOSITO... */

begin           end     ;           entero    real
identificador   ,       if          (         )
else            =       <=          >=        <>
<               >       num_entero  num_real  while
endwhile        :=      +           *     	  -
/				λ





/**	CONJUNTO PRIMEROS	**/
P(<programa>)			->	{begin}
P(<declaraciones>)		->	{entero,real}
P(<sig_declaraciones>)	->	{entero,real,λ}
P(<declaracion>)		->	{entero,real}
P(<tipo>)				->	{entero,real}
P(<lista_variables>)	->	{identificador}
P(<sig_lista_variables>)->	{',',λ}
P(<ordenes>)			->	{if,while,identificador}
P(<sig_ordenes>)		->	{if,while,identificador,λ}
P(<orden>)				->	{if,while,identificador}
P(<condicion>)			->	{if}
P(<sig_condicion>)		->	{end,else}
P(<comparacion>)		->	{identificador,num_entero,num_real}
P(<condicion_op>)		->	{=,<=,>=,<>,<,>}
P(<operador>)			->	{identificador,num_entero,num_real}
P(<numeros>)			->	{num_entero,num_real}
P(<bucle_while>)		->	{while}
P(<asignar>)			->	{identificador}
P(<expresion_arit>)		->	{(,identificador,num_entero,num_real}
P(<exp_arit>)			->	{+,*,-,/,λ}
P(<operador_arit>)		->	{+,*,-,/}

/**	CONJUNTO SIGUIENTES	**/
S(<programa>)			->	{$}
S(<declaraciones>)		->	{if,while,identificador}
S(<sig_declaraciones>)	->	{if,while,identificador}
S(<declaracion>)		->	{;}
S(<tipo>)				->	{identificador}
S(<lista_variables>)	->	{;}
S(<sig_lista_variables>)->	{;}
S(<ordenes>)			->	{end,else,endwhile}
S(<sig_ordenes>)		->	{end,else,endwhile}
S(<orden>)				->	{;}
S(<condicion>)			->	{;}
S(<sig_condicion>)		->	{;}
S(<comparacion>)		->	{)}
S(<condicion_op>)		->	{identificador,num_entero,num_real}
S(<operador>)			->	{),=,<=,>=,<>,<,>}
S(<numeros>)			->	{),=,<=,>=,<>,<,>,+,*,-,/,;}
S(<bucle_while>)		->	{;}
S(<asignar>)			->	{;}
S(<expresion_arit>)		->	{+,*,-,/,;,)}
S(<exp_arit>)			->	{+,*,-,/,;,)}
S(<operador_arit>)		->	{(,identificador,num_entero,num_real}


1)	S(A)	->	$
2)	A		->	αB
	2.1)	S(B)	->	S(A)

3)	A 		->	αBβ
	3.1)	S(B)	->	P(β) є λ
	3.2)	S(B)	->	P(β) U S(A)


