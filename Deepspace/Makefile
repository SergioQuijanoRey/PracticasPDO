# Ruby makefile

# Utilities
#===============================================================================
help:
		@echo "Ruby makefile options:"
		@echo "	make help: shows this information"
		@echo "	make check: checks for marks on the code"
check: ./utilities/check
		@./utilities/check
test: ./test/test_p1.rb ./test/test_p2.rb
		@echo "Running test no 1" 
		@echo "================================================================================"
		@./test/test_p1.rb
		@echo ""
		@echo "Running test no 2"
		@echo "================================================================================"
		@./test/test_p2.rb

