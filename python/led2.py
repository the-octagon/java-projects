#!/bin/python

import time
import RPi.GPIO as GPIO

BUTTON = 2
LED_ONE = 17
LED_TWO = 18
LED_THREE = 27
state = 0

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(BUTTON,GPIO.IN)
GPIO.setup(LED_ONE,GPIO.OUT)
GPIO.setup(LED_TWO,GPIO.OUT)
GPIO.setup(LED_THREE,GPIO.OUT)

while True:
	if (not GPIO.input(BUTTON)):
		if state == 0:
			state = 1
			GPIO.output(LED_ONE,GPIO.HIGH)
		elif state == 1:
			state = 2
			GPIO.output(LED_TWO,GPIO.HIGH)
		elif state == 2:
			state = 3
			GPIO.output(LED_THREE,GPIO.HIGH)
		elif state == 3:
			state = 0
			GPIO.output(LED_ONE,GPIO.LOW)
			GPIO.output(LED_TWO,GPIO.LOW)
			GPIO.output(LED_THREE,GPIO.LOW)
	time.sleep(0.15)
