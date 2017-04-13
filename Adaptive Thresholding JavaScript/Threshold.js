width = getWidth();
height = getHeight();
intensityTotal = 0;
histogram = newArray(256);
for(x = 0; x < width; x++)
{
    for(y = 0; y < height; y++)
    {
		value = getPixel(x,y);
		histogram[value]++;
    }
}

currentThreshold = (intensityTotal / (getWidth() * getHeight())); // say its 150
previousThreshold = 50;


for(i = 0; i<histogram.length;i++)
{
    intensityTotal += histogram[i] * i;
}

while(previousThreshold != currentThreshold) //------------------------- 
{
	objectCount = 0;
	backgroundCount = 0;
	totalObjectIntensity = 0;
	totalBackgroundIntensity = 0;

	for(i = 0; i<histogram.length;i++)
	{
		if(i <= previousThreshold)
		{
			objectCount += histogram[i];
		}
		else if(i > previousThreshold)
		{
		 	backgroundCount += histogram[i];
		}
	}
	for (i = 0; i < 256; i++)
    {
        
        if (i > currentThreshold)
        {
            totalBackgroundIntensity += (histogram[i] * i);
        }
        else if (i <= currentThreshold)
        {
            totalObjectIntensity += (histogram[i] * i);
        }
    }
	currentThreshold = previousThreshold;
	previousThreshold = (((totalObjectIntensity/objectCount) + (totalBackgroundIntensity/backgroundCount))/2);


}

for(x = 0; x < getWidth(); x++)
{
    for(y = 0; y < getHeight(); y++)
    {
		if (getPixel(x,y) <= currentThreshold)
	   {
		   setPixel(x,y,255);
	   }
	   else if(getPixel(x,y) > currentThreshold)
	   {
		   setPixel(x,y,0);
	   }
	}
}