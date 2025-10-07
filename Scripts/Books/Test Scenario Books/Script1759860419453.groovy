import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

response = WS.sendRequest(findTestObject('FakeRESTApi_Web V1/_api_v1_Books/POST_Book', [('url') : GlobalVariable.base_url]))

WS.verifyResponseStatusCode(response, 200)

// --- Test data ---
int id = 201

def createVars = [('id') : id, ('title') : 'Katalon In Action', ('description') : 'Demo testing IFG']

def updateVars = createVars.clone()

updateVars.title = 'Katalon In Action - 2nd Ed'

// --- POST create ---
def respCreate = WS.sendRequest(findTestObject('FakeRESTApi_Web V1/_api_v1_Books/POST_Book', [('url') : GlobalVariable.base_url
            , ('id') : id]))

WS.verifyResponseStatusCode(respCreate, 200)

WS.verifyElementPropertyValue(respCreate, 'title', 'Katalon In Action')

WS.verifyElementPropertyValue(respCreate, 'description', 'Demo testing IFG')

// --- GET by id ---
def respGet1 = WS.sendRequest(findTestObject('FakeRESTApi_Web V1/_api_v1_Books/GET_Books_byID', [('id') : id, ('url') : GlobalVariable.base_url]))

WS.verifyResponseStatusCode(respGet1, 200)

WS.verifyElementPropertyValue(respGet1, 'id', id)

WS.verifyElementPropertyValue(respGet1, 'title', 'Katalon In Action')

// --- PUT update ---
def respUpdate = WS.sendRequest(findTestObject('FakeRESTApi_Web V1/_api_v1_Books/PUT', [('id') : id]))

WS.verifyResponseStatusCode(respUpdate, 200)

WS.verifyElementPropertyValue(respUpdate, 'title', 'Katalon In Action - 2nd Ed')

// --- GET again ---
def respGet2 = WS.sendRequest(findTestObject('FakeRESTApi_Web V1/_api_v1_Books/GET_Books_byID', [('id') : id, ('url') : GlobalVariable.base_url]))

WS.verifyResponseStatusCode(respGet2, 200)

WS.verifyElementPropertyValue(respGet2, 'title', 'Katalon In Action - 2nd Ed')

