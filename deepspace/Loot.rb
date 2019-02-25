#encondig:utf-8

# WIP -- Translatio
# Clase que representa el botin que se obtiene al vencer a una nave enemiga
class Loot

	# Constructores
	#===========================================================================

	# Description:
	# 	Inicializador de la clase 
	# Parameters:
	# 	nSupplies: entero para parametrizar el numero de Supplies que da un botin
	# 	nWeapons: entero para parametrizar el numero de Weapons que da un botin
	# 	nShields: entero que parametriza el numero de Shields que da un botin
	# 	nHangars: entero que parametriza el numero de Hangars que da un botin
	# 	nMedals: entero que parametriza el numero de Medals que da un botin
	# Return:
	# 	Nil
	def initialize(_nSupplies, _nWeapons, _nShields, _nHangars, _nMedals)
		@nSupplies = _nSupplies
		@nWeapons = _nWeapons
		@nShields = _nShields
		@nHangars = _nHangars
		@nMedals = _nMedals
	end

	# Getters
	#===========================================================================

	# Description:
	# 	Consultor publico para numero de Supplies
	# Return
	#	Entero con el numero de Supplies
	def nSupplies
		return @nSupplies
	end

	# Description:
	# 	Consultor publico para numero de Weapons
	# Return
	#	Entero con el numero de Weapons
	def nWeapons
		return @nWeapons
	end
	
	# Description:
	# 	Consultor publico para numero de Shields
	# Return
	#	Entero con el numero de Shields
	def nShields
		return @nShields
	end

	# Description:
	# 	Consultor publico para numero de Hangars
	# Return
	#	Entero con el numero de Hangars
	def nHangars
		return @nHangars
	end

	# Description:
	# 	Consultor publico para numero de Medals
	# Return
	#	Entero con el numero de Medals
	def nMedals
		return @nMedals
	end
end
