precision mediump float; 

uniform sampler2D Texture0;
uniform sampler2D Texture1;
uniform float     distortPower;
uniform float     fTime0_X;
uniform float     hazeSpeed;


varying vec2 rm_Texcoord;

void main( void )
{

vec4 n = 2.0 * texture2D(Texture1, rm_Texcoord + vec2( 0.0, fTime0_X ) * hazeSpeed ) - 1.0;
vec2 newTC = n.xy * distortPower + rm_Texcoord;
 
gl_FragColor = texture2D( Texture0, newTC, 2.0 );

}