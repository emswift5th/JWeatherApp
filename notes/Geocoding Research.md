Verify city
	Would need to study OpenWeatherMap API information for this, but this would also need an agnostic implementation, as I intend to use multiple weather APIs in the future...

Can use OpenWeatherMap to get lat/lon coordinates of a postcode, provided in the format
`code,country`
ie
`ba138aa,GB`
`http://api.openweathermap.org/geo/1.0/zip?zip=BA147QP,GB&appid={API_KEY}`
This also returns a county for the UK, state for Germany...

Can use this geocoding API to get the correct city, and return coordinates.
Those coordinates can be used, quite simply, with the OWMAPI as already known:
`https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}`
