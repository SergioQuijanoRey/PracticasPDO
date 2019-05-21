# Ruby makefile

# Utilities
#===============================================================================
help:
		@echo "Ruby makefile options:"
		@echo "	make help: shows this information"
		@echo "	make check: checks for marks on the code"
		@echo "	make tests: runs all tests over the game"
		@echo "	make test1: runs fist test over the game"
		@echo "	make test2: runs second test over the game"
		@echo "	make test3: runs third test over the game"
		@echo "	make run: runs the main game"

check: ./utilities/check
		@./utilities/check

tests: ./test/test_p1.rb ./test/test_p2.rb
		@echo "Running test no 1" 
		@echo "================================================================================"
		@./test/test_p1.rb
		@echo ""
		@echo "Running test no 2"
		@echo "================================================================================"
		@./test/test_p2.rb
		@echo "Running test no 3"
		@echo "================================================================================"
		@./test/test_p3.rb

test1: ./test/test_p1.rb
		@echo "Running test no 1" 
		@echo "================================================================================"
		@./test/test_p1.rb

test2: ./test/test_p2.rb
		@echo "Running test no 2"
		@echo "================================================================================"
		@./test/test_p2.rb

test3: ./test/test_p3.rb
		@echo "Running test no 3"
		@echo "================================================================================"
		@./test/test_p3.rb

run: ./lib/PlayWithUI.rb
		@echo "Executing main game!"
		@ruby ./lib/PlayWithUI.rb
	
zip:
	@zip -r comprimido.zip .
