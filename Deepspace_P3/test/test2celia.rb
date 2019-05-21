require '../lib/Damage.rb'
require '../lib/enums.rb'
require '../lib/Weapon'
require '../lib/ShieldBooster'
require '../lib/GameUniverse'
require '../lib/SpaceStation'
require '../lib/SuppliesPackage'
require '../lib/EnemyStarShip'
require '../lib/Hangar'

puts "Creamos un objeto de la clase Damage"
array_weap = [Deepspace::WeaponType::LASER,Deepspace::WeaponType::MISSILE]
puts array_weap.to_s
puts Deepspace::WeaponType::LASER.to_s
daño = Deepspace::Damage.newSpecificWeapons(array_weap,2)
daño2 = Deepspace::Damage.newCopy(daño)
puts daño.to_s
puts daño2.to_s
arma = Deepspace::Weapon.new("PLASMA", Deepspace::WeaponType::PLASMA, 1)
#daño2.discardWeapon(arma)
#daño2.discardShieldBooster
puts daño2.hasNoEffect
arma2 = Deepspace::Weapon.new("PLASMA",Deepspace::WeaponType::PLASMA, 2)
array_arma=[arma, arma2]
pos =  daño.arrayContainsType(array_arma, Deepspace::WeaponType::MISSILE)
puts pos
escudo1 = Deepspace::ShieldBooster.new("escudo_uno",2,3)
escudo2 = Deepspace::ShieldBooster.new("escudo_dos",4,3)
array_escudo = [escudo1, escudo2]
puts " "
puts "Probamos adjust"
puts "Mostramos el daño que queremos hacer: "+daño.to_s
puts "Mostramos las armas de las que disponemos: "  +array_arma.to_s
puts "MOstramos los escudos de los que disponemos: "+array_escudo.to_s
puts "Mostramos el daño que podemos hacer: "
puts daño.adjust(array_arma,array_escudo).to_s
puts " "
puts "Probamos la otra variante de adjust: "
daño3 = Deepspace::Damage.newNumericWeapons(2,4)
puts daño3.adjust(array_arma, array_escudo).to_s
array_nuevo = [Deepspace::WeaponType::PLASMA, Deepspace::WeaponType::PLASMA]
puts " "
puts "Creamos un objeto damage con newNumericWeapons"
daño_num = Deepspace::Damage.newNumericWeapons(3,2)
puts daño_num.to_s
puts "Creamos un objeto Damage con newSpecificWeapons"
daño_esp = Deepspace::Damage.newSpecificWeapons(array_nuevo,3)
puts daño_esp.to_s

puts " "
puts "Probamos discard: "
puts daño3.to_s
daño3.discardWeapon(arma2)
daño3.discardWeapon(arma2)
daño3.discardWeapon(arma2)
puts daño3.to_s
puts " "
puts "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nProbamos la otra variante de discard: "
j = 0
puts "Mostramos el daño actual"
for i in daño_esp.weapons do
  puts i.to_s
  j+=1
end

puts "Descartamos este arma:"
puts arma2.to_s

puts "PRIMERO ESTO"
puts daño_esp.to_s
daño_esp.discardWeapon(arma2)
puts "Daño ha quedado asi:"
puts daño_esp.to_s

puts " "
#FALTA DISCARDWEAPON




puts "Probamos SpaceStation"
provisiones = Deepspace::SuppliesPackage.new(2,3,4)
estacion = Deepspace::SpaceStation.new("nave1", provisiones)
puts estacion.to_s
puts "assignFuelValue:"
estacion.assignFuelValue(400)
puts estacion.fuelUnits
puts "cleanPendingDamage:"
estacion.cleanPendingDamage
puts estacion.pendingDamage
puts "probamos cleanUpMountedItems: "
puts estacion.weapons.to_s
estacion.cleanUpMountedItems
puts estacion.weapons.to_s
#FALTAN COSAS POR PROBAR


puts " "
puts "Probamos EnemyStarShip"
bot = Deepspace::Loot.new(1,2,3,4,5)
dan = Deepspace::Damage.newNumericWeapons(2,5)
enemigo = Deepspace::EnemyStarShip.new("enem",3, 2, bot, dan)
puts enemigo.to_s
enemigo2 = Deepspace::EnemyStarShip.newCopy(enemigo)
puts enemigo2.to_s
puts enemigo.receiveShot(2)
#EnemyStarShip probado y funciona


puts " "
puts "Probamos Hangar:"
hangar = Deepspace::Hangar.new(20)
puts "addWeapon"
arma_hangar = Deepspace::Weapon.new("MISSILE", Deepspace::WeaponType::MISSILE, 2)
puts hangar.addWeapon(arma_hangar)
puts hangar.addWeapon(arma_hangar)
puts hangar.weapons.to_s
puts "addShieldBooster: "
esc_hangar = Deepspace::ShieldBooster.new("esc_hangar", 3,4)
puts hangar.addShieldBooster(esc_hangar)
puts hangar.addShieldBooster(esc_hangar)
puts hangar.shieldBoosters.to_s
puts "spaceAvailable:"
puts hangar.spaceAvailable
puts "removeShieldBooster:"
puts hangar.removeShieldBooster(0)
puts hangar.shieldBoosters.to_s
puts "removeWeapon:"
puts hangar.removeWeapon(2)
puts hangar.weapons.to_s
#Hangar probado y funciona
puts " "


puts "Probamos SpaceStation"
provisiones = Deepspace::SuppliesPackage.new(2,3,4)
estacion = Deepspace::SpaceStation.new("nave1", provisiones)
puts estacion.to_s
puts "assignFuelValue:"
estacion.assignFuelValue(40)
puts estacion.fuelUnits
puts "cleanPendingDamage:"
estacion.cleanPendingDamage
puts estacion.pendingDamage

puts "receiveHangar:"
puts estacion.receiveHangar(hangar)
puts "receiveShieldBooster: "
escudo_estacion = Deepspace::ShieldBooster.new("esc_estacion",3,0)
puts estacion.receiveShieldBooster(escudo_estacion)
puts estacion.hangar.to_s
puts "discardShieldBoosterinHangar"
puts estacion.discardShieldBoosterInHangar(2)
puts estacion.hangar.to_s
puts "discardWeaponinHangar"
puts estacion.discardWeaponInHangar(0)
puts estacion.hangar.to_s
puts "speed: "
puts estacion.speed
puts "move: "
puts estacion.fuelUnits
puts estacion.speed
estacion.move
puts estacion.fuelUnits
puts "receiveSupplies: "
sum_nuevos = Deepspace::SuppliesPackage.new(34,21,9)
puts estacion.shieldPower
estacion.receiveSupplies(sum_nuevos)
puts estacion.shieldPower
puts "receiveWeapon: "
arma_estacion = Deepspace::Weapon.new("PLASMA", Deepspace::WeaponType::PLASMA, 0)
puts estacion.receiveWeapon(arma_estacion)
puts estacion.hangar.to_s
puts "validState: "
puts estacion.validState
puts "mountShieldBooster: "
estacion.mountShieldBooster(1)
estacion.mountShieldBooster(0)
puts estacion.shieldBoosters
puts estacion.hangar
puts "mountWeapon:"
estacion.mountWeapon(1)
puts estacion.hangar
puts estacion.weapons
puts "probamos cleanUpMountedItems: "
puts estacion.shieldBoosters.to_s
#estacion.cleanUpMountedItems
puts estacion.shieldBoosters.to_s
puts "setPendingDamage: "
puts estacion.weapons.to_s
#puts estacion.shieldBoosters.to_s
daño_estacion = Deepspace::Damage.newSpecificWeapons(array_arma,3)
estacion.setPendingDamage(daño_estacion)
puts estacion.pendingDamage
#FALTAN COSAS POR PROBAR


puts "Creamos objeto de la clase GameUniverse"
juego = Deepspace::GameUniverse.new
#puts juego.to_s
#FALTAN COSAS POR PROBAR


if (Deepspace::WeaponType::LASER == Deepspace::WeaponType::LASER)
  puts "hola"
end

array = Array.new
array << Deepspace::WeaponType::LASER
array << Deepspace::WeaponType::LASER
array << Deepspace::WeaponType::MISSILE
arma = Deepspace::Weapon.new("hol", Deepspace::WeaponType::LASER, 9)
array.delete(arma.type)
for i in array do
  puts i.to_s
end