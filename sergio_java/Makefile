# Description
# 	Java project makefile
# 	It's more for utilities than for compilation process
# Author:
# 	Sergio Quijano Rey
# 	sergiquijano@gmail.com

# Makefile parameters
#===============================================================================
zip_file = "./deepspace.zip"

# Utilities
#===============================================================================
help:
	@echo "Makefile options:"
	@echo "	make help: shows this help"
	@echo "	make check: checks for marks on the code"
	@echo "	make zip: exports the project in a zip file"

check: ./utilities/check
	@./utilities/check

zip:
	@zip -r $(zip_file) .
