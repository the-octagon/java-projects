#!/usr/bin/python
import RPi.GPIO as GPIO
import sys

REDPIN = 22
GREENPIN = 4

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(4,GPIO.OUT)
GPIO.setup(22,GPIO.OUT)

def getState(pin):
	GPIO.setup(pin, GPIO.IN)
	state = GPIO.input(pin)
	GPIO.setup(pin, GPIO.OUT)
	return state

def onOff(pin):
	if getState(pin) == True:
		GPIO.output(pin,GPIO.LOW)
	elif getState(pin) == False:
		GPIO.output(pin, GPIO.HIGH)

while True:
	color = 0
	kill = ""

	print "Choose a color: or e to exit"
	print "1. red"
	print "2. green"
	choice = raw_input("")

	try:
		color = int(choice)
	except:
		if choice == "e":
			GPIO.output(GREENPIN,GPIO.LOW)
			GPIO.output(REDPIN,GPIO.LOW)
			sys.exit()

	if color == 1:
		onOff(REDPIN)
	elif color == 2:
		onOff(GREENPIN)
	else:
		print "please make a valid selection."

	print "\n\n\n"
