# Combate Pokémon

Ejercicio por Emma Fernández y Sergio Misas

## Introducción

Este ejercicio consiste en crear un programa que simule un combate entre dos
entrenadores Pokémon. Las normas del combate estarán simplificadas, ya que
si se pidieran los cálculos y mecánicas originales sería muy complicado.

## Equipos Pokémon y movimientos

Cada entrenador tendrá un equipo de hasta 6 Pokémon cuya información se
encontrará en ficheros JSON (se deberá comprobar el número de Pokémon del
fichero). Además, se proporcionará otro fichero CSV con los movimientos y sus
características. El jugador podrá elegir cuál de los dos equipos quiere usar.

## Movimientos

Cada Pokémon tendrá un máximo de 4 movimientos (se debe comprobar al leer el
fichero), cada uno con su tipo y potencia. Los movimientos harán daño en función
de su potencia, las compatibilidades de tipos, el STAB (_Same Type Attack
Bonus_) y la defensa del Pokémon rival.

Un movimiento eficaz contra un Pokémon hará el doble de daño, mientras que
un movimiento poco eficaz hará la mitad. Ambos cálculos serán realizados
por cada tipo del Pokémon defensor. Si el movimiento es del mismo tipo que
el Pokémon que lo utiliza, se le aplicará un bonus del 50% de su potencia.
Por último, el daño resultante se multiplicará por el ratio de daño del
Pokémon que recibe el ataque.

Cada movimiento tendrá un número de PP (Puntos de Poder) que se se irán
gastando según se utilice el movimiento. Cuando un movimiento tenga 0 PP, ya
no se podrá utilizar.

## Estadísticas de los Pokémon

Para simplificar los cálculos, un Pokémon tendrá uno o dos tipos, además de
sus movimientos y una estadística de defensa o ratio de daño recibido. Esta
estadística (representada como un número entre 0 y 1) se usará para calcular
qué porcentaje del daño total recibirá el Pokémon, sustituyendo a la defensa
de los juegos. También tendrán un número máximo de PS (Puntos de Salud).

## Tipos

Para calcular la eficacia de un movimiento, se usará la tabla de tipos de
los juegos, con los x0 cambiados por x0.5 para simplificar. Si no conoces la
tabla, búscala en Internet.

## Combate

El combate se realizará en turnos. Cada turno, el jugador podrá elegir un
movimiento para el Pokémon actual. El movimiento elegido se ejecutará y
después, si el Pokémon rival tiene PS, se ejecutará el movimiento del rival,
que se escogerá aleatoriamente. Si el Pokémon rival no tiene PS, se elegirá
otro Pokémon del equipo rival y saldrá a combatir. Alternativamente, el
jugador puede emplear su turno para cambiar de Pokémon, recibiendo un ataque
del Pokémon rival al entrar el nuevo Pokémon.

El combate finaliza cuando un entrenador no tiene ningún Pokémon con PS. Al
acabar el combate, se mostrará un informe con los siguientes datos:

- El entrenador ganador.
- El número de turnos que ha durado el combate.
- La información de los Pokémon de cada entrenador (PS y PP restantes).