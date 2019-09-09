export interface weatherInterface {
    
    duplex?: String;
    oneShot?: String;
    timezone?: String;
    currently?: {
        summary?: String;
        icon?: String;
        nearestStormDistance?: String;
        temperature?: String
    }
    daily: {
        summary: String;
        icon: String;
        data: [
            {
                time: String;
                summary: String;
                icon: String;
                temperatureMin: String;
                temperatureMax: String;
            }
        ]
    };
    offset: String
}