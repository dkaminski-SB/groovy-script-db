// Updates Access Token in Auth profile. Test Step with such script can be put at beggining of Test Suite/Case or before Test Step that will use Auth Token.

def accessToken = context.expand( '${Get Token#Response#$[\'sessionid\']}' ) // Gets Access token value form Another Test Step or Custom Property(on Project level).
def authProfile = "Sample Profile" // Specify name of OAuth Profile in ReadyAPI witch 

// Sets Access Token directly in to AuthProfile called "Sample Profile" (Profile must be created first in ReadyAPI)
context.testCase.testSuite.project.getAuthRepository().getEntry(authProfile).setAccessToken( token)

// logs current API Token
log.info "Your current access token is: " +  context.testCase.testSuite.project.getAuthRepository().getEntry(authProfile).getAccessToken()